package com.yi.mall.product.feign;

import com.yi.common.dto.es.SkuESModel;
import com.yi.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-search")
public interface SearchFeginService {

    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuESModel> skuESModels);
}


