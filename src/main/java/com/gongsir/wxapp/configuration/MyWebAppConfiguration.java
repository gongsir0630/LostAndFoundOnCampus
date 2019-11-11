package com.gongsir.wxapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/25 16:25
 * 编码不要畏惧变化，要拥抱变化
 */
@Configuration
public class MyWebAppConfiguration implements WebMvcConfigurer {
    @Value("${upload.location}")
    private String path;

    /**
     * 配置资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadImg/**").addResourceLocations("file:"+path);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置放行url
        List<String> stringList = new ArrayList<>();
        stringList.add("/wxApi/user/login/**");
        stringList.add("/uploadImg/**");
        registry.addInterceptor(new MyInterceptor())
                //拦截所有请求
                .addPathPatterns("/wxApi/**")
                .excludePathPatterns(stringList);
    }
}
