package com.cell.first.springboot.config;

import com.cell.first.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class EnableAppConfig {
    @Autowired
    UserProperties userProperties;

    public void print() {
        System.out.println(userProperties);
    }
}
