package com.graduation.config;

import com.graduation.interceptor.FileDownloadInterceptor;
import com.graduation.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description web配置类
 *
 * @author shaoming
 * @date 2021/8/17 10:00
 * @since 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    public FileDownloadInterceptor fileDownloadInterceptor(){
        return new FileDownloadInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).excludePathPatterns("/s/download/**","/s/**","/check/**","/error/**","/install","/login","/doLogin","/doSignUp","/install/**","/static/**")
                .addPathPatterns("/**");
//        registry.addInterceptor(fileDownloadInterceptor()).addPathPatterns("/s/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOrigins("*")
                    .allowedHeaders("*");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
