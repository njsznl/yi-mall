package com.yi.mall.product.feign;

import com.yi.common.dto.SkuReductionDTO;
import com.yi.common.dto.SpuBoundsDTO;
import com.yi.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yi
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveFullReductionInfo(@RequestBody SkuReductionDTO dto);


    @RequestMapping("/coupon/spubounds/saveSpuBounds")
    R saveSpuBounds(@RequestBody SpuBoundsDTO spuBounds);
}
