package com.yi.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.dto.SkuReductionDTO;
import com.yi.common.utils.PageUtils;
import com.yi.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 15:50:16
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionDTO dto);

}

