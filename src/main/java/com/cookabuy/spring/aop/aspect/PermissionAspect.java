package com.cookabuy.spring.aop.aspect;

import com.cookabuy.configuration.GlobalConfiguration;
import com.cookabuy.entity.service.dto.PublishRecommendItemForm;
import com.cookabuy.entity.service.dto.RecommendItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 该切面用于处理推荐商品的权限的控制(爆款专区，热销类目，商品管理)，由于在推荐商品的时候前端会传来page_name参数，
 * 可根据每个page自定义每个页面进入需要的权限，一下bean 配置每个page的需要的权限
 * @see GlobalConfiguration#getRecommendItemPermissionMap()
 * @author yejinbiao
 * @create 2017-01-09-下午4:38
 */
@Aspect
@Component
@Slf4j
public class PermissionAspect {

    @Resource(name = "addItemPermissions")
    private Map<String, String> addItemPermissions;
    @Resource(name = "publishItemPermissions")
    private Map<String, String> publishItemPermissions;

    @Before(value = "@annotation(com.cookabuy.spring.aop.annotation.CheckPermission) && args(parameter)")
    public void checkPermission( Object parameter) {
        String permission = null;
        if (parameter instanceof PublishRecommendItemForm) {
            String pageName = ((PublishRecommendItemForm) parameter).getPage();
            permission = publishItemPermissions.get(pageName);
        }else if(parameter instanceof RecommendItemEntity) {
           RecommendItemEntity itemEntity = (RecommendItemEntity) parameter;
           String page = itemEntity.getPageName();
           permission = addItemPermissions.get(page);
       }
        /**
         * 拿当前用户拥有的权限与此页面进入需要的权限
         * @see com.cookabuy.shiro.realm.OperationUserRealm#doGetAuthorizationInfo(PrincipalCollection)
         */
        boolean permitted = SecurityUtils.getSubject().isPermitted(permission);
        if (!permitted) {
            /**
             *该异常与shiro的没有权限时抛出的异常一致，并被spring的全局捕获
             * @see ExceptionHandlerAdvice#catchAuthorizationException()
             */
            throw new AuthorizationException();
        }

    }
}
