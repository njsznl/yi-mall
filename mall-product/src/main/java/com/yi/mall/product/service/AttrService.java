package com.yi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.product.entity.AttrEntity;

import java.util.Map;

/**
 * ��Ʒ����
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 05:32:23
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

