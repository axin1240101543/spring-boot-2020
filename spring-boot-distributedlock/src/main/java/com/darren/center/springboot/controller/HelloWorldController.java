package com.darren.center.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
public class HelloWorldController extends BaseController{

    @RequestMapping("/")
    public String index() {
        return "redirect:/hello";
    }

    @RequestMapping("/hello")
    public String hello(Map<String, Object> paramMap) {
        /** 默认Map的内容会放大请求域中，页面可以直接使用Thymeleaf取值*/
        paramMap.put("name", "张三");
        paramMap.put("age", 35);
        return "hello";
    }


}
