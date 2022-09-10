package com.yi.mall.coupon.dao;

import com.yi.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 15:50:16
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
