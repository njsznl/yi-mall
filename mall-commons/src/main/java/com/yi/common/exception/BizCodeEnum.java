package com.yi.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 错误编码和错误信息枚举
 *
 * @author : xiao on 2022/9/13 21:26
 * @version : 1.0
 */
@Getter
@RequiredArgsConstructor
public enum BizCodeEnum {

    /**
     * 异常枚举
     */
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(100001, "参数格式异常"),




    VALID_SMS_EXCEPTION(10002,"短信发送频率太高，稍等一会发送!"),
    PRODUCT_UP_EXCEPTION(11001,"商城上架异常"),
    NO_STOCK_EXCEPTION(14001,"商品锁定库存失败"),
    USERNAME_EXSIT_EXCEPTION(15001,"用户名存在"),
    PHONE_EXSIT_EXCEPTION(15002,"手机号存在"),
    USERNAME_PHONE_VALID_EXCEPTION(15003,"账号或者密码错误");


    private final int code;
    private final String msg;

}
