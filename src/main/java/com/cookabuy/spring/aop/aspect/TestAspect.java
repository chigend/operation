package com.cookabuy.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author yejinbiao
 * @create 2016-12-30-14:52
 */
@Aspect
//@Component
public class TestAspect {

    @Before(value = "@annotation(com.cookabuy.spring.aop.annotation.RequiresPermission) && args(student)")
    public void checkPermission(JoinPoint point) {
       System.out.println("hello aop");
    }
}
