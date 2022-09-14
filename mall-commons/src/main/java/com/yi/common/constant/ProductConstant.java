package com.yi.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 商品模块的常量
 */
public class ProductConstant {
    @Getter
    @RequiredArgsConstructor
    public enum AttrEnum {
        /**
         * 属性
         */
        ATTR_TYPE_BASE(1, "基本属性"),
        ATTR_TYPE_SALE(0, "销售属性");
        private final int code;
        private final String msg;
    }

    @Getter
    @RequiredArgsConstructor
    public enum StatusEnum {
        /**
         * 状态
         */
        SPU_NEW(0, "新建"),
        SPU_UP(1, "上架"),
        SPU_DOWN(2, "下架");
        private final int code;
        private final String msg;
    }

}
