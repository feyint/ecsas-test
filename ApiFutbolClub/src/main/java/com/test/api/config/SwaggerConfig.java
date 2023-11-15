/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.api.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author feyin
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
 
    @Bean
    public Docket api() {
 
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jcodepoint.customerserviceswagger"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
        }   
     
        private ApiInfo getApiInfo() {
            return new ApiInfo(
                    "Customer Service API",
                    "Customer Service API Documentation",
                    "1.0",
                    "https://jcodepoint.com/",
                    new Contact("jcodepoint", "https://jcodepoint.com", "jcodepoint@gmail.com"),
                    "LICENSE",
                    "LICENSE URL",
                    Collections.emptyList()
                    );
        }
     
}