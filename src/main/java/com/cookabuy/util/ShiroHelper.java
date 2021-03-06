package com.cookabuy.util;

import com.cookabuy.entity.operation.po.OperationUser;
import org.apache.shiro.SecurityUtils;

/**
 * @author yejinbiao
 * @create 2016-12-21-16:47
 */

public class ShiroHelper {
    public static OperationUser getCurrentUser(){

        return (OperationUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getCurrentUserId(){
        return getCurrentUser() == null ? null: getCurrentUser().getId();
    }

    public static String getCurrentUsername(){
        return getCurrentUser() == null ? null : getCurrentUser().getUsername();
    }

    public static void setAttribute(String key, Object value) {
        if (key != null) {
            SecurityUtils.getSubject().getSession().setAttribute(key, value);
        }
    }

    public static Object getAttribute(String key) {
        if (key != null) {
            return SecurityUtils.getSubject().getSession().getAttribute(key);
        }
        return null;
    }
}
