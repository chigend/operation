package com.cookabuy.spring.aop.aspect;

import com.cookabuy.util.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.aspectj.lang.annotation.AfterThrowing;

/**
 * @author yejinbiao
 * @create 2016-12-30-14:52
 */
//@Aspect
//@Component
public class TestAspect {
    @AfterThrowing(pointcut = "org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor.assertAuthorized(..)", throwing = "e")
    public Result catchAuthorizedException(AuthorizationException e) {
        System.out.println(e);
        return new Result();
    }
}
