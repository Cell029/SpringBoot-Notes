package com.cell.first.springboot.test;

import com.cell.first.springboot.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigTest {
    @Autowired
    private AppConfig appConfig;

    @Test
    public void test() {
        appConfig.printInfo();
    }
}
