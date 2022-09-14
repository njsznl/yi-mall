package com.yi.mall.product.controller;

import com.yi.common.utils.PageUtils;
import com.yi.common.utils.R;
import com.yi.mall.product.service.AttrService;
import com.yi.mall.product.vo.AttrResponseVo;
import com.yi.mall.product.vo.AttrVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 商品属性
 *
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 14:07:13
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;


    /**
     * 默认列表数据
     *
     * @param params    params
     * @param catelogId 分类ID
     * @param attrType  属性类型：0-销售属性，1-基本属性，2-既是销售属性又是基本属性
     * @return page
     */
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseList(@RequestParam Map<String, Object> params
            , @PathVariable("catelogId") Long catelogId
            , @PathVariable("attrType") String attrType
    ) {
        PageUtils page = attrService.queryBasePage(params, catelogId, attrType);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    @RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrResponseVo attr = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrVO vo) {
        attrService.saveAttr(vo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrVO attr) {
        attrService.updateBaseAttr(attr);
        return R.ok();
    }

    /**
     * 删除
     * 如果是删除基本属性那么还需要将关联的属性组的信息也要删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIdsDetails(attrIds);
        return R.ok();
    }

}
