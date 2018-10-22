package com.ferhat.multitenant.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket userApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("userApi")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/user/**"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket databaseApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("databaseApi")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/database/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo("MultiTenant Api", "MultiTenant Api App", "1.0.0",
                null,
                new Contact("Ferhat","","ferhat.bedr@gmail.com"),
                null,null, Collections.emptyList());
    }
}