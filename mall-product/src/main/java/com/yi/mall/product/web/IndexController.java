package com.yi.mall.product.web;

import com.yi.mall.product.entity.CategoryEntity;
import com.yi.mall.product.service.CategoryService;
import com.yi.mall.product.vo.Catalog2VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/home"})
    public String index(Model model) {

        // 查询出所有的一级分类的信息
        List<CategoryEntity> list = categoryService.getLeve1Category();
        model.addAttribute("categorys", list);
        return "index";
    }
    @ResponseBody
    @RequestMapping("/index/catalog.json")
    public Map<String, List<Catalog2VO>> getCatalog2JSON(){
        Map<String, List<Catalog2VO>> map = categoryService.getCatelog2JSON();
        return map;
    }
}
