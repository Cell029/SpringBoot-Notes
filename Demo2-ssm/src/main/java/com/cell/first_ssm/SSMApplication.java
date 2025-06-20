package com.cell.first_ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 扫描 Mapper 接口
@MapperScan(basePackages = {"com.cell.first_ssm.mapper"})
@SpringBootApplication
public class SSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class, args);
    }

}
