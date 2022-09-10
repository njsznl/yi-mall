package com.yi.mall.order.dao;

import com.yi.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 16:04:19
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
