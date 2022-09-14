package com.yi.mall.product.mapstruct;

import com.yi.common.base.BaseMapper;
import com.yi.mall.product.entity.AttrEntity;
import com.yi.mall.product.vo.AttrResponseVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author : xiao on 2022/9/15 2:42
 * @version : 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttrEntityMapper extends BaseMapper<AttrResponseVo, AttrEntity> {
}
