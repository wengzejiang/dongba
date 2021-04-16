package com.cy.pj.common.config;

import com.cy.pj.common.web.TimeAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Package: com.cy.pj.common.config
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/4/15 0015 21:34
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeAccessInterceptor()).addPathPatterns("/user/doLogin");//设置要拦截的路径
    }
}
