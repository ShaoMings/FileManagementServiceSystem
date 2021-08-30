package com.graduation.config;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaoming
 */
@Configuration
public class WebMvcConfiguration {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        webServerFactory.addConnectorCustomizers((TomcatConnectorCustomizer) connector ->{
            // 允许url中带特殊字符
            connector.setProperty("relaxedQueryChars","+/");
        });
        return webServerFactory;
    }
}
