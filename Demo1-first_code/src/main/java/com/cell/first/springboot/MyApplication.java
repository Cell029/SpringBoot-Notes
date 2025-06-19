package com.cell.first.springboot;

import com.cell.first.springboot.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Date;

// 所有 springboot 应用的主入口程序必须使用 @SpringBootApplication 注解
@SpringBootApplication
public class MyApplication {

    @Bean
    public Date getNowDate(){ // 方法名作为bean的id
        return new Date();
    }

    // 主入口，运行 main 方法就是启动服务器
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MyApplication.class, args);
        Date dateBean1 = applicationContext.getBean(Date.class);
        System.out.println(dateBean1);
        Date dateBean2 = applicationContext.getBean("getNowDate", Date.class);
        System.out.println(dateBean2);
        AppConfig appConfig = applicationContext.getBean(AppConfig.class);
        appConfig.printInfo();
        applicationContext.close();

        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
