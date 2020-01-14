package com.iflytek.springboot.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {



    @RequestMapping(value = "/swagger")
    public String hello(){

        return "/swaggerPage";
    }
}
