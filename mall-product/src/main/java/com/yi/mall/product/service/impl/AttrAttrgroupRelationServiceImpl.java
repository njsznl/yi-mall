package com.yi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.common.utils.PageUtils;
import com.yi.common.utils.Query;
import com.yi.mall.product.dao.AttrAttrgroupRelationDao;
import com.yi.mall.product.entity.AttrAttrgroupRelationEntity;
import com.yi.mall.product.mapstruct.AttrGroupRelationMapper;
import com.yi.mall.product.service.AttrAttrgroupRelationService;
import com.yi.mall.product.vo.AttrGroupRelationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yi
 */
@RequiredArgsConstructor
@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity>
        implements AttrAttrgroupRelationService {

    private final AttrGroupRelationMapper attrGroupRelationMapper;
    private final AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public void deleteRelation(AttrGroupRelationVO[] vos) {
        attrAttrgroupRelationDao.removeBatchRelation(attrGroupRelationMapper.toEntity(Arrays.asList(vos)));
    }

    @Override
    public void saveBatch(List<AttrGroupRelationVO> vos) {
        this.saveBatch(attrGroupRelationMapper.toEntity(vos));
    }

}