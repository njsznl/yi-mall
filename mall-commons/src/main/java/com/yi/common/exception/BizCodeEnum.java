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
    VALID_EXCEPTION(100001, "参数格式异常");

    private final int code;
    private final String msg;

}
