package com.yi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * Ʒ�Ʒ������
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 05:32:23
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

