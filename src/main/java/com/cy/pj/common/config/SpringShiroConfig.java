package com.cy.pj.common.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Package: com.cy.pj.common.config
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/4/11 0011 15:20
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Configuration//由spring容器管理的一个配置类对象
public class SpringShiroConfig {

    //DefaultWebSecurityManager对象为shiro框架的核心
    @Bean//返回值交给Spring管理
    public SecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager sManager=new DefaultWebSecurityManager();
        sManager.setRealm(realm);
        return sManager;
    }

    //ShiroFilterFactoryBean基于此对象创建顾虑器工程，通过过滤器对象创建过滤器(filter)，通过过滤器对用户请求进行过滤
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory (SecurityManager securityManager) {
        ShiroFilterFactoryBean sfBean=new ShiroFilterFactoryBean();
        sfBean.setSecurityManager(securityManager);
        //设置登陆页面跳转url
        sfBean.setLoginUrl("/doLoginUI");
        //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
        LinkedHashMap<String,String> map=new LinkedHashMap<>();
        //静态资源允许匿名访问:"anon"
        map.put("/bower_components/**","anon");
        map.put("/build/**","anon");
        map.put("/dist/**","anon");
        map.put("/plugins/**","anon");
        //输入账号密码路径
        map.put("/user/doLogin","anon");
        //退出页面url
        map.put("/doLogout","logout");//logout由shiro框架指定
        //除了匿名访问的资源,其它都要认证("authc")后访问
        map.put("/**","authc");
        sfBean.setFilterChainDefinitionMap(map);
        return sfBean;
    }

}
