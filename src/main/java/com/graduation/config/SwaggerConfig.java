package com.graduation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description Swagger配置类
 *
 * @author shaoming
 * @date 2021/7/20 15:13
 * @since 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("Springboot整合Swagger2")
                        .description("整合详细信息介绍")
                        .version("2.0")
                        .contact(new Contact("shaoming","www.shaoming.online",
                                "a18477019945@gmail.com"))
                        .license("The Apache License")
                        .licenseUrl("www.baidu.com")
                        .build());
    }
}
