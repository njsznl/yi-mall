package com.yi.mall.order.feign;

import com.yi.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 商品接口
 *
 * @author : yi on 2022/9/10 17:20
 * @version : 1.0
 */
@FeignClient(name = "mall-product")
public interface ProductService {


    @GetMapping("/product/brand/all")
    R queryAllBrand();
}
