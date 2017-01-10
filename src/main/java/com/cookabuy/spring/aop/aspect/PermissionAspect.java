package com.cookabuy.spring.aop.aspect;

import com.cookabuy.entity.service.dto.RecommendItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yejinbiao
 * @create 2017-01-09-下午4:38
 */
@Aspect
@Component
@Slf4j
public class PermissionAspect {

    @Resource(name = "permissionMap")
    private Map<String, String> permissionMap;

    @Before(value = "@annotation(com.cookabuy.spring.aop.annotation.RequiresPermission) && args(parameter)")
    public void checkPermission( Object parameter) {
       if (parameter instanceof RecommendItemEntity) {
           RecommendItemEntity itemEntity = (RecommendItemEntity) parameter;
           String page = itemEntity.getPageName();
           String permission = permissionMap.get(page);
           boolean permitted = SecurityUtils.getSubject().isPermitted(permission);
           if (!permitted) {
               throw new AuthorizationException();
           }
       }

    }
}
