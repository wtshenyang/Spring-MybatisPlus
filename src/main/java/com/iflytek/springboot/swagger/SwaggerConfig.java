package com.iflytek.springboot.swagger;

import com.iflytek.springboot.base.interfaces.Sy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration   //加入配置注解
@EnableSwagger2  //开启swagger注解
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("沈阳-扫描包")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iflytek.springboot.controller"))
                .build();
    }

    //多人开发配置
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("沈阳-扫描注解")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(Sy.class))
                .build();
    }


    //配置Swagger信息Apiinfo
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact1 = new Contact("沈阳", "localhost:8080/app/swagger-ui.html", "18155947@qq.com");
        return new ApiInfo("Swagger调试", "调试接口", "1.0", "urn:tos", contact1, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
