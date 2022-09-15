package com.yi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.product.entity.SpuInfoEntity;
import com.yi.mall.product.vo.SpuInfoVO;

import java.util.Map;

/**
 * spu信息
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 14:07:13
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuInfoVO spuInfoVo);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void up(Long spuId);

}

