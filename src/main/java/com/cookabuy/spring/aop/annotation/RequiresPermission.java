package com.cookabuy.spring.aop.annotation;

import java.lang.annotation.*;

/**
 * 2017/1/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPermission {
    String value() default "";
}
