package com.yi.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 库存模块的常量
 *
 * @author yi
 */
public class WareConstant {

    /**
     * 采购单状态
     */
    @Getter
    @RequiredArgsConstructor
    public enum PurchaseStatusEnum {
        /**
         *
         */
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        RECEIVE(2, "已领取"),
        FINISH(3, "已完成"),
        HAS_ERROR(4, "有异常");
        private final int code;
        private final String msg;
    }

    /**
     * 采购需求状态
     */
    @Getter
    @RequiredArgsConstructor
    public enum PurchaseDetailStatusEnum {
        /**
         *
         */
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        BUYING(2, "正在采购"),
        FINISH(3, "已完成"),
        HAS_ERROR(4, "采购失败");
        private final int code;
        private final String msg;
    }
}
