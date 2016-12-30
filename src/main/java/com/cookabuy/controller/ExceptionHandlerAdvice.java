package com.cookabuy.controller;

import com.cookabuy.util.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yejinbiao
 * @create 2016-12-30-15:20
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    /**
     *由于shiro对没有权限的的invoke method 会报AuthorizationException异常，所以通过spring的全局异常捕获
     * 进行特殊处理，返回相应的结果
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result catchAuthorizationException() {
        Result result = new Result();
        result.setError("no permission");
        return result;
    }
}
