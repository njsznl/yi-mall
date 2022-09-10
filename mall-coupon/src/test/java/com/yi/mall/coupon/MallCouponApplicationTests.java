package com.yi.mall.coupon;

import com.yi.mall.coupon.entity.CouponEntity;
import com.yi.mall.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallCouponApplicationTests {

    @Autowired
    private CouponService couponService;

    @Test
    void contextLoads() {
        CouponEntity entity = new CouponEntity();
        entity.setCouponName("啊啊啊啊啊啊");
        entity.setCode("aaaaaa");
        couponService.save(entity);
    }

}
