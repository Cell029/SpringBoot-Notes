package com.cell.notes.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j // 这是 lombok 的注解，用来维护一个 log 日志对象
@MapperScan("com.cell.notes.web.mapper")
@SpringBootApplication
public class MySpringBootApplication {

    public static void main(String[] args) {
		/*SpringApplication springApplication = new SpringApplication(MySpringBootApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);*/
        SpringApplication.run(MySpringBootApplication.class, args);

        log.trace("trace级别日志");
        log.debug("debug级别日志");
        log.info("info级别日志");
        log.warn("warn级别日志");
        log.error("error级别日志");
    }

}
