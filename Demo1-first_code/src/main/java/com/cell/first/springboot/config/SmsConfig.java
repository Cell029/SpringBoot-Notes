package com.cell.first.springboot.config;

import com.cell.first.properties.AliyunSmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliyunSmsProperties.class)
public class SmsConfig {
    private AliyunSmsProperties aliyunSmsProperties;

    // 只有一个构造方法时可以省略 @Autowired，而这个 bean 可以通过构造方法注入，所以可以都不写 @Autowired
    public SmsConfig(AliyunSmsProperties aliyunSmsProperties) {
        this.aliyunSmsProperties = aliyunSmsProperties;
    }

    public void print() {
        System.out.println(aliyunSmsProperties);
    }
}
