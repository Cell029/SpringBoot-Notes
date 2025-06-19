package com.cell.first.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 所有 springboot 应用的主入口程序必须使用 @SpringBootApplication 注解
@SpringBootApplication
public class MyApplication {
    // 主入口，运行 main 方法就是启动服务器
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
