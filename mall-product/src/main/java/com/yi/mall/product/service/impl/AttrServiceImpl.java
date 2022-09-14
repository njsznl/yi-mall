package com.yi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.common.constant.ProductConstant;
import com.yi.common.utils.PageUtils;
import com.yi.common.utils.Query;
import com.yi.mall.product.dao.AttrAttrgroupRelationDao;
import com.yi.mall.product.dao.AttrDao;
import com.yi.mall.product.dao.AttrGroupDao;
import com.yi.mall.product.entity.AttrAttrgroupRelationEntity;
import com.yi.mall.product.entity.AttrEntity;
import com.yi.mall.product.entity.AttrGroupEntity;
import com.yi.mall.product.mapstruct.AttrGroupRelationMapper;
import com.yi.mall.product.service.AttrGroupService;
import com.yi.mall.product.service.AttrService;
import com.yi.mall.product.vo.AttrGroupRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yi
 */
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrGroupRelationMapper attrGroupRelationMapper;
    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private AttrGroupDao attrGroupDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据属性组编号查询对应的基本信息
     *
     * @param attrgroupId 属性分组ID
     * @return 当前分组下的所有属性
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        // 1. 根据属性组编号从 属性组和基本信息的关联表中查询出对应的属性信息
        List<AttrAttrgroupRelationEntity> list = attrAttrgroupRelationDao
                .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        // 2.根据属性id数组获取对应的详情信息
        return list.stream()
                .map((entity) -> this.getById(entity.getAttrId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 解除属性组和基本属性的关联关系
     * 也就是删除 属性组和属性关联表中的记录
     *
     * @param vos 属性vo
     */
    @Override
    public void deleteRelation(AttrGroupRelationVO[] vos) {
        attrAttrgroupRelationDao.removeBatchRelation(attrGroupRelationMapper.toEntity(Arrays.asList(vos)));
    }

    @Override
    public PageUtils getNoAttrRelation(Map<String, Object> params, Long attrgroupId) {
        // 1.查询当前属性组所在的类别编号
        AttrGroupEntity attrGroupEntity = attrGroupService.getById(attrgroupId);
        // 获取到对应的分类id
        Long catelogId = attrGroupEntity.getCatelogId();
        // 2.当前分组只能关联自己所属的类别下其他的分组没有关联的属性信息。
        // 先找到这个类别下的所有的分组信息
        List<AttrGroupEntity> group = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        // 获取属性组的编号集合
        List<Long> groupIds = group.stream().map(AttrGroupEntity::getAttrGroupId).collect(Collectors.toList());
        // 然后查询出类别信息下所有的属性组已经分配的属性信息
        List<AttrAttrgroupRelationEntity> relationEntities =
                attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", groupIds));
        List<Long> attrIds = relationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        // 根据类别编号查询所有的属性信息并排除掉上面的属性信息即可
        // 这其实就是需要查询出最终返回给调用者的信息了  分页  带条件查询
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>()
                .eq("catelog_id", catelogId)
                // 查询的是基本属性信息，不需要查询销售属性信息
                .eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        // 然后添加排除的条件
        if (attrIds.size() > 0) {
            wrapper.notIn("attr_id", attrIds);
        }
        // 还有根据key的查询操作
        String key = (String) params.get("key");
        if (StringUtils.hasText(key)) {
            wrapper.and((w) -> w.eq("attr_id", key).or().like("attr_name", key));
        }
        // 查询对应的相关信息
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}