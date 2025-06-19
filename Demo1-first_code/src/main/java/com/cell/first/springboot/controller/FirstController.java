package com.cell.first.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 这个 Controller 类想要被 spring 容器管理，就必须和 Spring Boot 主入口程序在同级目录或其子目录下
@Controller
@ResponseBody
public class FirstController {
    @RequestMapping("/hello")
    public String index(){
        return "Hello World!";
    }
}
