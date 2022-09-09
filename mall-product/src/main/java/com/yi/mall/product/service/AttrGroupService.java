package com.yi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * ���Է���
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 05:32:22
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

