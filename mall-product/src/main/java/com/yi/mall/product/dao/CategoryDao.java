package com.yi.mall.product.dao;

import com.yi.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 05:32:22
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
