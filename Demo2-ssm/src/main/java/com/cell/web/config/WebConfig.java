package com.cell.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 使用注册器 registry 绑定 path、pattern 以及真实静态资源路径
        registry.addResourceHandler("/static/**") // 配置访问路径
                .addResourceLocations("classpath:/static1/", "classpath:/static2/"); // 配置静态资源存放路径
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/myHome").setViewName("home");
    }

    // 配置新的消息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlHttpMessageConverter());
    }

}
