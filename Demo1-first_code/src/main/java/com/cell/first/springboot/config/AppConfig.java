package com.cell.first.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 将配置文件上的值绑定到属性上
    @Value("${myapp.path}")
    private String appPath;

    public void printInfo() {
        System.out.println(appPath);
    }
}
