package com.yi.mall.ware.feign;

import com.yi.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yi
 */
@FeignClient("mall-product")
public interface ProductFeignService {

    /**
     * 当然我们也可以通过网关来调用商品服务
     *
     * @param skuId skuId
     * @return R
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    R info(@PathVariable("skuId") Long skuId);
}
