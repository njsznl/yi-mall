package com.yi.mall.search.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yi.common.dto.es.SkuESModel;
import com.yi.mall.search.config.MallElasticSearchConfiguration;
import com.yi.mall.search.constant.ESConstant;
import com.yi.mall.search.service.MallSearchService;
import com.yi.mall.search.vo.SearchParam;
import com.yi.mall.search.vo.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public SearchResult search(SearchParam param) {
        SearchResult result = null;
        // 1. ?????????????????????
        SearchRequest request = buildSearchRequest(param);

        try {
            // 2.??????????????????
            SearchResponse response = client.search(request, MallElasticSearchConfiguration.COMMON_OPTIONS);
            // 3.?????????????????????????????????SearchResult
            result = buildSearchResult(response, param);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * ?????????????????????
     * ??????????????????????????????
     * ??????(????????????????????????????????????????????????)
     * ??????
     * ??????
     * ??????
     * ????????????
     *
     * @param param
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ESConstant.PRODUCT_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // ??????????????????????????????
        // 1.??????bool??????
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 1.1 ??????????????????
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("subTitle", param.getKeyword()));
        }
        // 1.2 ?????????????????????
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }
        // 1.3 ?????????????????????
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            boolQuery.filter(QueryBuilders.termsQuery("brandId", param.getBrandId()));
        }
        // 1.4 ???????????????
        if (param.getHasStock() != null) {
            boolQuery.filter(QueryBuilders.termQuery("hasStock", param.getHasStock() == 1));
        }
        // 1.5 ???????????????????????????
        if (!StringUtils.isEmpty(param.getSkuPrice())) {
            String[] msg = param.getSkuPrice().split("_");
            RangeQueryBuilder skuPrice = QueryBuilders.rangeQuery("skuPrice");
            if (msg.length == 2) {
                // ????????? 200_300
                skuPrice.gte(msg[0]);
                skuPrice.lte(msg[1]);
            } else if (msg.length == 1) {
                // ????????? _300  200_
                if (param.getSkuPrice().endsWith("_")) {
                    // ????????? 200_
                    skuPrice.gte(msg[0]);
                }
                if (param.getSkuPrice().startsWith("_")) {
                    // ????????? _300
                    skuPrice.lte(msg[0]);
                }
            }
            boolQuery.filter(skuPrice);
        }
        // 1.6 ????????????????????? attrs=20_8??????:10??????&attrs=19_64GB:32GB
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            for (String attrStr : param.getAttrs()) {
                BoolQueryBuilder boolNestedQuery = QueryBuilders.boolQuery();
                // attrs=19_64GB:32GB ???????????????????????? _ ?????????
                String[] attrStrArray = attrStr.split("_");
                // ???????????????
                String attrId = attrStrArray[0];
                // 64GB:32GB  ??????????????????
                String[] values = attrStrArray[1].split(":");
                // ??????????????????
                boolNestedQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                boolNestedQuery.must(QueryBuilders.termsQuery("attrs.attrValue", values));

                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", boolNestedQuery, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            }
        }
        sourceBuilder.query(boolQuery);

        // 2.??????
        if (!StringUtils.isEmpty(param.getSort())) {
            // sort=salaCount_asc/desc
            String[] s = param.getSort().split("_");
            SortOrder order = s[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC;
            sourceBuilder.sort(s[0], order);
        }
        // 3.????????????
        // Integer pageNum; // ??????
        if (param.getPageNum() != null) {
            // ????????????????????? pageSize = 5
            // pageNum:1 from:0  [0,1,2,3,4]
            // pageNum:2 from:5 [5,6,7,8,9]
            // from = ( pageNum - 1 ) * pageSize
            sourceBuilder.from((param.getPageNum() - 1) * ESConstant.PRODUCT_PAGESIZE);
            sourceBuilder.size(ESConstant.PRODUCT_PAGESIZE);
        }

        // 4. ????????????
        if (!StringUtils.isEmpty(param.getKeyword())) {
            // ???????????????????????????????????????????????????????????????
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("subTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            sourceBuilder.highlighter(highlightBuilder);
        }

        // 5.????????????
        // 5.1 ???????????????
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg");
        brand_agg.field("brandId");
        brand_agg.size(50);
        // ??????????????????
        brand_agg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(10));
        brand_agg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(10));
        sourceBuilder.aggregation(brand_agg);

        // 5.2 ???????????????
        TermsAggregationBuilder catalog_agg = AggregationBuilders.terms("catalog_agg");
        catalog_agg.field("catalogId");
        catalog_agg.size(10);
        // ??????????????????
        catalog_agg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(10));
        sourceBuilder.aggregation(catalog_agg);

        // 5.3 ???????????????
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrs");
        // ??????id??????
        TermsAggregationBuilder attr_id_agg = AggregationBuilders.terms("attr_id_agg");
        attr_id_agg.field("attrs.attrId");
        attr_id_agg.size(10);
        // ??????id??????????????? ????????????????????????
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(10));
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(10));
        attr_agg.subAggregation(attr_id_agg);
        sourceBuilder.aggregation(attr_agg);

        System.out.println("--->" + sourceBuilder.toString());
        searchRequest.source(sourceBuilder);

        return searchRequest;
    }

    /**
     * ????????????????????????????????????SearchResult??????
     *
     * @param response
     * @return
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {
        SearchResult result = new SearchResult();
        SearchHits hits = response.getHits();
        // 1.???????????????????????????
        SearchHit[] products = hits.getHits();
        List<SkuESModel> esModels = new ArrayList<>();
        if (products != null && products.length > 0) {
            for (SearchHit product : products) {
                String sourceAsString = product.getSourceAsString();
                // ???json????????????????????????fastjson?????????SkuESModel??????

                ObjectMapper objectMapper = new ObjectMapper();
                SkuESModel model = JSONObject.parseObject(sourceAsString, SkuESModel.class);
                if (StringUtils.hasText(param.getKeyword())) {
                    // ????????????????????????
                    HighlightField subTitle = product.getHighlightFields().get("subTitle");
                    String subTitleHighlight = subTitle.getFragments()[0].string();
                    model.setSubTitle(subTitleHighlight); // ????????????
                }
                esModels.add(model);
            }
        }
        result.setProducts(esModels);
        Aggregations aggregations = response.getAggregations();
        // 2.??????????????????????????????????????????
        ParsedLongTerms brand_agg = aggregations.get("brand_agg");
        List<? extends Terms.Bucket> buckets = brand_agg.getBuckets();
        // ???????????????????????????
        List<SearchResult.BrandVO> brandVOS = new ArrayList<>();
        if (buckets != null && buckets.size() > 0) {
            for (Terms.Bucket bucket : buckets) {
                SearchResult.BrandVO brandVO = new SearchResult.BrandVO();
                // ???????????????key
                String keyAsString = bucket.getKeyAsString();
                brandVO.setBrandId(Long.parseLong(keyAsString)); // ?????????????????????
                // ?????????????????????????????????????????????????????????
                ParsedStringTerms brand_img_agg = bucket.getAggregations().get("brand_img_agg");
                List<? extends Terms.Bucket> bucketsImg = brand_img_agg.getBuckets();
                if (bucketsImg != null && bucketsImg.size() > 0) {
                    String img = bucketsImg.get(0).getKeyAsString();
                    brandVO.setBrandImg(img);
                }
                // ???????????????????????????
                ParsedStringTerms brand_name_agg = bucket.getAggregations().get("brand_name_agg");
                String breadName = brand_name_agg.getBuckets().get(0).getKeyAsString();
                brandVO.setBrandName(breadName);

                brandVOS.add(brandVO);
            }
        }
        result.setBrands(brandVOS);
        // 3.?????????????????????????????????????????????
        ParsedLongTerms catalog_agg = aggregations.get("catalog_agg");
        List<? extends Terms.Bucket> bucketsCatalogs = catalog_agg.getBuckets();
        // ???????????????????????????????????????
        List<SearchResult.CatalogVO> catalogVOS = new ArrayList<>();
        if (bucketsCatalogs != null && bucketsCatalogs.size() > 0) {
            for (Terms.Bucket bucket : bucketsCatalogs) {
                SearchResult.CatalogVO catalogVO = new SearchResult.CatalogVO();
                String keyAsString = bucket.getKeyAsString(); // ?????????????????????
                catalogVO.setCatalogId(Long.parseLong(keyAsString));
                // ?????????????????????
                ParsedStringTerms catalog_name_agg = bucket.getAggregations().get("catalog_name_agg");
                String catalogName = catalog_name_agg.getBuckets().get(0).getKeyAsString();
                catalogVO.setCatalogName(catalogName);
                catalogVOS.add(catalogVO);
            }
        }
        result.setCatalogs(catalogVOS);
        // 4.?????????????????????????????????????????????
        ParsedNested attr_agg = aggregations.get("attr_agg");
        ParsedLongTerms attr_id_agg = attr_agg.getAggregations().get("attr_id_agg");
        List<? extends Terms.Bucket> bucketsAttr = attr_id_agg.getBuckets();
        List<SearchResult.AttrVo> attrVos = new ArrayList<>();
        if (bucketsAttr != null && bucketsAttr.size() > 0) {
            for (Terms.Bucket bucket : bucketsAttr) {
                SearchResult.AttrVo attrVo = new SearchResult.AttrVo();
                // ?????????????????????
                String keyAsString = bucket.getKeyAsString();
                attrVo.setAttrId(Long.parseLong(keyAsString));
                // ?????????????????? ??????????????? ??? ????????????
                ParsedStringTerms attr_name_agg = bucket.getAggregations().get("attr_name_agg");
                String attrName = attr_name_agg.getBuckets().get(0).getKeyAsString(); // ???????????????
                attrVo.setAttrName(attrName);
                ParsedStringTerms attr_value_agg = bucket.getAggregations().get("attr_value_agg");
                if (attr_value_agg.getBuckets() != null && attr_value_agg.getBuckets().size() > 0) {
                    List<String> values = attr_value_agg.getBuckets().stream().map(item -> {
                        String keyAsString1 = item.getKeyAsString();
                        return keyAsString1;
                    }).collect(Collectors.toList());
                    attrVo.setAttrValue(values);
                }
                attrVos.add(attrVo);
            }

        }
        result.setAttrs(attrVos);
        // 5. ????????????  ????????? ???????????????  ?????????
        long total = hits.getTotalHits().value;
        result.setTotal(total);// ??????????????????  6 /5  1+1
        result.setPageNum(param.getPageNum()); // ???????????????
        long totalPage = total % ESConstant.PRODUCT_PAGESIZE == 0 ? total / ESConstant.PRODUCT_PAGESIZE : (total / ESConstant.PRODUCT_PAGESIZE + 1);
        result.setTotalPages((int) totalPage); // ??????????????????
        List<Integer> navs = new ArrayList<>();
        for (int i = 1; i <= totalPage; i++) {
            navs.add(i);
        }
        result.setNavs(navs);
        return result;
    }
}
