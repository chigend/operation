package com.cookabuy.shiro.realm;

import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.repository.operation.OperationUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
public class OperationUserRealm extends AuthorizingRealm {
    @Autowired
    private OperationUserRepository userRepository;

    @Override
    public void setName(String name) {
        super.setName("operationRealm");
    }

    /**
     * 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        log.info("the current username is {}",username);
        OperationUser u = userRepository.findByUsername(username);
        if(u == null){
            log.info("no user match with {}",username);
            return null;
        }
        return new SimpleAuthenticationInfo(u,u.getPassword(),this.getName());
    }
    //授权方法      在检查是否具有shiro权限的时候调用 如果没有配置缓存器的话，每次检查都会调用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(principals == null){
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        return info;
    }

}
