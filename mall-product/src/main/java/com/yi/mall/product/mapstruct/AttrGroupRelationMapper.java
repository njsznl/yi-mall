package com.yi.mall.product.mapstruct;

import com.yi.common.base.BaseMapper;
import com.yi.mall.product.entity.AttrAttrgroupRelationEntity;
import com.yi.mall.product.vo.AttrGroupRelationVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * attr group relation mapper
 *
 * @author : xiao on 2022/9/14 4:04
 * @version : 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttrGroupRelationMapper extends BaseMapper<AttrGroupRelationVO, AttrAttrgroupRelationEntity> {
}
