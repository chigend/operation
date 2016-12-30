package com.cookabuy.spring.aop.aspect;

import com.cookabuy.repository.operation.OperationRepository;
import com.cookabuy.util.Result;
import com.cookabuy.util.ShiroHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * 该类是切面，由于在获取菜单项数据的同时需要获取当前用户在当前菜单页面所具备的能操作的按钮，在代码中称operation
 * 由于该动作在所有菜单项中都有，所以用aop的方式来动态的切入并加入operation
 * @see com.cookabuy.spring.aop.annotation.MenuItem
 * @author yejinbiao
 * @create 2016-12-30-9:50
 */
@Aspect
@Component
public class OperationAspect {
    @Autowired(required = true)
    private HttpServletRequest request;
    @Autowired
    private OperationRepository operationRepository;
    @Around("@annotation(com.cookabuy.spring.aop.annotation.MenuItem)")
    public Result getOperationAvaiable(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer menuId = Integer.valueOf(request.getParameter("menuId"));
        Object o = joinPoint.proceed();
        if (menuId == null) {
            return (Result) o;
        }
        Result result = null;
        if (o instanceof Result){
            result = (Result) o;
            Integer userId = ShiroHelper.getCurrentUserId();
            List<String> operations = operationRepository.findOperationAvaiableByMenuIdAndUserId(userId, menuId);
            result.addData("operations", operations);
        }

        return  result == null ? (Result) o : result;

    }
}
