package com.cookabuy.spring.aop.aspect;

import com.cookabuy.entity.tmp.Student;
import com.cookabuy.util.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

/**
 * @author yejinbiao
 * @create 2016-12-30-14:52
 */
//@Aspect
//@Component
public class TestAspect {

    @Before(value = "@annotation(com.cookabuy.spring.aop.annotation.RequiresPermission) && args(parameter)")
    public void checkPermission(JoinPoint point, Object parameter) {
        if (parameter instanceof Result) {
            System.out.println("result");
        } else if (parameter instanceof Student) {
            System.out.println("student");
        }

    }
}
