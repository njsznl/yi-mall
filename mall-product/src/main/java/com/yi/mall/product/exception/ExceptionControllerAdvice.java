package com.yi.mall.product.exception;

import com.yi.common.exception.BizCodeEnum;
import com.yi.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 *
 * @author : xiao on 2022/9/13 20:55
 * @version : 1.0
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.yi.mall.product.controller")
public class ExceptionControllerAdvice {

    /**
     * validate统一异常
     *
     * @param e MethodArgumentNotValidException
     * @return R
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handlerValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>(e.getFieldErrors().size());
        e.getFieldErrors().forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data", errorMap);
    }


    /**
     * 系统其他异常
     *
     * @param throwable throwable
     * @return R
     */
    @ExceptionHandler(Throwable.class)
    public R handlerException(Throwable throwable) {
        log.error("错误信息记录：{}", throwable.getMessage(), throwable);
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(),
                BizCodeEnum.UNKNOWN_EXCEPTION.getMsg()).put("data", throwable.getMessage());
    }
}
