package com.gongsir.wxapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
     * wxApi 域下的拦截器
     */
    @Bean
    public WxApiInterceptor getWxApiInterceptor(){
        return new WxApiInterceptor();
    }

    @Bean
    public AdminApiInterceptor getAdminApiInterceptor(){
        return new AdminApiInterceptor();
    }

    /**
     * 配置资源映射
     * @param registry 注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadImg/**").addResourceLocations("file:"+path);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 配置拦截器
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置 /wxApi 域下的放行url
        List<String> wxApiList = new ArrayList<>();
        wxApiList.add("/wxApi/user/login/**");
        wxApiList.add("/uploadImg/**");
        registry.addInterceptor(getWxApiInterceptor())
                //拦截所有wxApi域下的请求
                .addPathPatterns("/wxApi/**")
                .excludePathPatterns(wxApiList);

        //配置 /admin 域下的放行url
        List<String> adminApiList = new ArrayList<>();
        //获取验证码
        adminApiList.add("/admin/admin/code/**");
        //注册
        adminApiList.add("/admin/admin/register/**");
        //登录
        adminApiList.add("/admin/admin/login/**");
        //vx消息推送
        adminApiList.add("/admin/wx/**");
        //账号验证
        adminApiList.add("/admin/admin/check/**");
        registry.addInterceptor(getAdminApiInterceptor())
                //拦截所有admin域下的请求
                .addPathPatterns("/admin/**")
                .excludePathPatterns(adminApiList);
    }
}
