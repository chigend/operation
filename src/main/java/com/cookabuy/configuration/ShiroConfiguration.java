package com.cookabuy.configuration;

import com.cookabuy.properties.CredentialsMatcherProperties;
import com.cookabuy.shiro.filter.LoginFilter;
import com.cookabuy.shiro.realm.OperationUserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(CredentialsMatcherProperties.class)

public class ShiroConfiguration {

    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifeCycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();

    }
    //spring-boot中注册DelegatingFilterProxy
    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        registration.addUrlPatterns("/*");
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        return registration;
    }


    //安全管理器
    @Bean(name="securityManager")
    public SecurityManager getSecurityManager(EhCacheManager cacheManager,OperationUserRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealm(realm);
        return securityManager;
    }
    //缓存管理器
    @Bean
    public EhCacheManager getEhcacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        //设置ehcache缓存的配置文件的位置
        cacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return  new EhCacheManager();
    }
    //开启spring aop
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator d = new DefaultAdvisorAutoProxyCreator();
        d.setProxyTargetClass(true);
        return d;
    }
    //开启shiro注解的使用
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthoirzationAdvisor(SecurityManager manager){
        AuthorizationAttributeSourceAdvisor aa = new AuthorizationAttributeSourceAdvisor();
        aa.setSecurityManager(manager);
        return aa;
    }
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");

//	        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        // 登录成功后要跳转的连接
//	        shiroFilterFactoryBean.setSuccessUrl("/user");
//	        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        Map<String,Filter> filters = new HashMap<String,Filter>();
        filters.put("authc",getFormFilter());
        Map<String,String> definition = new HashMap<String,String>();
        definition.put("/login", "anon");
        definition.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(definition);
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

    @Bean
    public PassThruAuthenticationFilter getFormFilter(){
        PassThruAuthenticationFilter filter = new PassThruAuthenticationFilter();
        return filter;
    }
    //	@Bean
//	public FormAuthenticationFilter getFormFilter(){
//		return new FormAuthenticationFilter();
//	}
    @Bean
    public OperationUserRealm getUserRealm(CredentialsMatcherProperties matcherProperties){
        OperationUserRealm realm = new OperationUserRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(matcherProperties.getHashAlgorithmName());
        matcher.setHashIterations(matcherProperties.getHashIterations());
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**原先shiro会对未认证的url进行重定向至login(get)进行认证，但在后台只提供接口功能时，
     * 前端页面无法进行重定向，所以对login请求进行过滤，并返回相应的过滤结果
     * {@link LoginFilter }
     *
     */

    @Bean
    public FilterRegistrationBean registLoginFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/login");
        registration.setEnabled(true);
        return registration;
    }
}
