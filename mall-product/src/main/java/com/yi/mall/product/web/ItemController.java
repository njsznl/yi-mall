package com.yi.mall.product.web;

import com.yi.mall.product.entity.SkuInfoEntity;
import com.yi.mall.product.service.SkuInfoService;
import com.yi.mall.product.vo.SpuItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品详情控制器
 *
 * @author : yi on 2022/10/14 12:27
 * @version : 1.0
 */
@Controller
public class ItemController {

    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 根据skuId查询商品详细信息
     *
     * @param skuId skuId
     * @param model 视图Model
     * @return string
     */
    @GetMapping("/{skuId}.html")
    public String item(@PathVariable Long skuId, Model model) {
        SpuItemVO itemVO = skuInfoService.item(skuId);
        model.addAttribute("item",itemVO);
        return "item";
    }
}
