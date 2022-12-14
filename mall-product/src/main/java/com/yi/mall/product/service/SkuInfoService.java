package com.yi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.product.entity.SkuInfoEntity;
import com.yi.mall.product.vo.SpuItemVO;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 14:07:13
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageByCondition(Map<String, Object> params);

    List<SkuInfoEntity> getSkusBySpuId(Long spuId);

    SpuItemVO item(Long skuId);

}

