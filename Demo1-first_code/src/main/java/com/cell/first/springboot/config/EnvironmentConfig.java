package com.cell.first.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Arrays;
import java.util.Map;

@Configuration
public class EnvironmentConfig {
    @Autowired
    private Environment env;

    public void printActProfiles() {
        String[] activeProfiles = env.getActiveProfiles();
        System.out.println("当前激活的配置文件：" + Arrays.toString(activeProfiles));
    }

    public void printSysProperties() {
        String javaVersion = env.getProperty("java.version");
        String osName = env.getProperty("os.name");
        System.out.println("Java版本：" + javaVersion);
        System.out.println("操作系统名称：" + osName);
    }

    public void printSysEnvVariables() {
        String path = env.getProperty("PATH"); // Windows 是 PATH，Linux/Mac 也是 PATH
        String user = env.getProperty("USERNAME"); // Windows 下有效
        System.out.println("系统环境变量 PATH：" + path);
        System.out.println("用户 USER：" + user);
    }

    public void printComLineArguments() {
        String port = env.getProperty("server.port");
        System.out.println("命令行设置的端口号：" + port);
    }

    public void printPropertySourceList() {
        for (PropertySource<?> propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            System.out.println("属性来源: " + propertySource.getName());
            if (propertySource instanceof MapPropertySource) {
                Map<String, Object> source = ((MapPropertySource) propertySource).getSource();
                source.forEach((key, value) -> {
                    System.out.println(key + " = " + value);
                });
            }
        }

    }
}
