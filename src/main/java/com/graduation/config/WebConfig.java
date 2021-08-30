package com.graduation.config;

import com.graduation.interceptor.FileDownloadInterceptor;
import com.graduation.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        registry.addInterceptor(loginInterceptor()).excludePathPatterns("/s/**","/error/**","/install","/login","/install/**","/static/**")
                .addPathPatterns("/**");
        registry.addInterceptor(fileDownloadInterceptor()).addPathPatterns("/s/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
