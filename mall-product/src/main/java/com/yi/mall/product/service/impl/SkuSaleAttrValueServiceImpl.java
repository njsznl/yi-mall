package com.yi.mall.product.service.impl;

import com.yi.mall.product.vo.SkuItemSaleAttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.common.utils.PageUtils;
import com.yi.common.utils.Query;

import com.yi.mall.product.dao.SkuSaleAttrValueDao;
import com.yi.mall.product.entity.SkuSaleAttrValueEntity;
import com.yi.mall.product.service.SkuSaleAttrValueService;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {


    @Autowired
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Override
    public List<SkuItemSaleAttrVo> getSkuSaleAttrValueBySpuId(Long spuId) {
        return skuSaleAttrValueDao.getSkuSaleAttrValueBySpuId(spuId);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

}