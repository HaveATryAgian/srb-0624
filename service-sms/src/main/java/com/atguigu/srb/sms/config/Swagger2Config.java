package com.atguigu.srb.sms.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket coreApiConfig() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("尚融宝短信系统-API文档")
                .description("本文档描述了尚融宝短信系统接口")
                .version("1.0")
                .contact(new Contact("Jack", "http://atguigu.com", "55317332@qq.com"))
                .build();

        Docket adminApi = new Docket(DocumentationType.SWAGGER_2)
                .groupName("短信Api接口")
                .apiInfo(apiInfo)
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
        return adminApi;
    }


}
