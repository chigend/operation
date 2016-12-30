package com.cookabuy.spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.*;

/**
 * 该注解该为自定义，只适用于当前运营后台系统的业务，所有被该注解的方法都表示该方法
 * 为菜单项方法，即在获取当前菜单项数据的同时获取菜单页面中能操作的按钮operation
 * @see com.cookabuy.spring.aop.aspect.OperationAspect#getOperationAvaiable(ProceedingJoinPoint)
 * 2016-12-30
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuItem {
}
