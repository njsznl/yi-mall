package com.yi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.common.utils.PageUtils;
import com.yi.mall.product.entity.AttrEntity;
import com.yi.mall.product.vo.AttrGroupRelationVO;
import com.yi.mall.product.vo.AttrResponseVo;
import com.yi.mall.product.vo.AttrVO;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 14:07:13
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    PageUtils getNoAttrRelation(Map<String, Object> params, Long attrgroupId);

    void saveAttr(AttrVO attr);

    PageUtils queryBasePage(Map<String, Object> params, Long catelogId, String attrType);

    AttrResponseVo getAttrInfo(Long attrId);

    void updateBaseAttr(AttrVO attr);

    void removeByIdsDetails(Long[] attrIds);

    List<Long> selectSearchAttrIds(List<Long> attrIds);

}


