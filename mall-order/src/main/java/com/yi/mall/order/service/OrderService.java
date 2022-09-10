package com.yi.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 16:04:19
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

