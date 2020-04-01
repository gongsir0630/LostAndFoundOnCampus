package com.gongsir.wxapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述：api文档维护
 * @author gongsir
 * @date 2020/2/16 13:45
 * 编码不要畏惧变化，要拥抱变化
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //api扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.gongsir.wxapp.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api文档界面信息
     * @return ApiInfo
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("西柚失物招领小程序API文档")
                .description("更新人：龚涛")
                .contact(myContact())
                .version("1.0")
                .build();
    }

    /**
     * 联系信息
     * @return 联系信息封装
     */
    private Contact myContact(){
        return new Contact("龚涛","http://gongsir.club","gongsir0630@gmail.com");
    }
}
