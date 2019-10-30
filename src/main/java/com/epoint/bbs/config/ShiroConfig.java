package com.epoint.bbs.config;
import com.epoint.bbs.core.UserRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro主要分为三个核心api
 * subject:用户主体（将操作交给SecurityManager）
 * SecurityManager 安全管理器（关联realm）
 * Realm连接数据的桥梁
 */

@Configuration
public class ShiroConfig {
    /**
     * 创建shiroFilterfactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactory(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        //修改跳转的登录页面 注意此处要经过controller跳转
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/");
        //添加shiro内置的过滤器
        /**
         * shiro内置过滤器，可以实现权限相关的拦截
         *      常用的过滤器：
         *      anon:无需认证（登录）也可以访问
         *      authc：必须认证才可以访问  org.apache.shiro.web.filter.authc.FormAuthenticationFilter 所有标注为需要认证的路径都会走
         *      此过滤器
         *      user：如果使用remeberMe可以直接访问
         *      perms： 该资源必须资源权限才可以访问
         *      role： 该资源必须得到角色权限才可以访问
         */
        Map<String,String> filtermap=new HashMap<>();
        //配置时需要注意顺序
         filtermap.put("/login/*","anon");
         filtermap.put("/static/**", "anon");
         filtermap.put("/css/**","anon");
        filtermap.put("/images/**","anon");
        filtermap.put("/json/**","anon");
        filtermap.put("/layui/**","anon");
        filtermap.put("/mods/**","anon");
        //向map中添加过滤器 前面为请求后面为权限
        /**
         * /* 指只能匹配到一级目录 ，即只能匹配到类路径下的文件夹 而不能匹配到其子文件夹
         * /** 指多级目录 即能匹配到类路径下的子文件夹
         */
        filtermap.put("/user/register.do","anon");
        filtermap.put("/user/login.do","anon");
        filtermap.put("/user/logout.do","logout");
         filtermap.put("/**","authc");
         factoryBean.setFilterChainDefinitionMap(filtermap);
        return factoryBean;
    }
    /**
     * 创建defaultWebSecurityManager
     */
     @Bean
     public DefaultWebSecurityManager getDefaultWebSecurityManager(SessionManager sessionManager){
         DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
         securityManager.setSessionManager(sessionManager);
         //关联realm
         securityManager.setRealm(this.getRealm());
         return  securityManager;
     }
    /**
     * 创建 realm
     */
     @Bean
     public UserRealm getRealm(){
         return new UserRealm();
     }
    /**
     * shiro sesion管理器 代替servlet session管理
     */
    @Bean
   public DefaultWebSessionManager sessionManager(BbsConfig bbsConfig){
      DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        //定时查询所有session是否过期的时间
        sessionManager.setSessionValidationInterval(bbsConfig.getSessionValidationInterval() * 1000);
        //设置session过期时间
        sessionManager.setGlobalSessionTimeout(bbsConfig.getSessionInvalidateTime()* 1000);
        //删除过期session
        sessionManager.setDeleteInvalidSessions(true);
        //是否定时检查session
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置cookie
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        cookie.setHttpOnly(true);
        /**
         * session 以cookie为支撑 每次访问时会带上sessionid（名称为shiroCookie）即设置的cookie
         */
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
   }
}
