package com.yi.mall.product.dao;

import com.yi.mall.product.entity.SkuSaleAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.mall.product.vo.SkuItemSaleAttrVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sku销售属性&值
 * 
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 14:07:13
 */
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {





    List<SkuItemSaleAttrVo> getSkuSaleAttrValueBySpuId(Long spuId);

}
