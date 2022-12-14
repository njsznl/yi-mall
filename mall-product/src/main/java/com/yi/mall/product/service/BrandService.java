package com.yi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 14:07:13
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);

}

