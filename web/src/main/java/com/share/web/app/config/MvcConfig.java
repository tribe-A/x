package com.share.web.app.config;

import com.share.web.app.interceptor.AGCInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private AGCInterceptor agcInterceptor;

    public MvcConfig(AGCInterceptor agcInterceptor) {
        this.agcInterceptor = agcInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(agcInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/storage/callback");
    }
}
