package com.graduation.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Description Shiro的配置类
 *
 * @author shaoming
 * @date 2021/8/17 11:15
 * @since 1.0
 */
@Configuration
public class ShiroConfig {

    /**
     *  注册shiro 页面标签支持
     * @return ShiroDialect
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     *  session管理
     * @return  DefaultWebSessionManager
     */
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     *  安全管理
     * @return SecurityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(customRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }


    /**
     *  身份认证与授权
     * @return  ShiroRealm
     */
    @Bean
    public ShiroRealm customRealm(){
        return new ShiroRealm();
    }


    /**
     *  身份认证规则设置
     * @param securityManager 安全管理对象
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/s/**","anon");
        filterChainDefinitionMap.put("/check/**","anon");
        filterChainDefinitionMap.put("/callback/**","anon");
        filterChainDefinitionMap.put("/install","anon");
        filterChainDefinitionMap.put("/peers/doAdd","anon");
        filterChainDefinitionMap.put("/install/**","anon");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/doLogin","anon");
        filterChainDefinitionMap.put("/doSignUp","anon");
        filterChainDefinitionMap.put("/logout","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/error/**","anon");
        filterChainDefinitionMap.put("/**","user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     *  记住我 cookie设置
     * @return SimpleCookie
     */
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // cookie 设置30天有效期 2592000
        simpleCookie.setMaxAge(1800);
        return simpleCookie;
    }

    /**
     *  记住我 cookie 管理
     * @return CookieRememberMeManager
     */
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager crm = new CookieRememberMeManager();
        crm.setCookie(rememberMeCookie());
        crm.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return crm;
    }


}
