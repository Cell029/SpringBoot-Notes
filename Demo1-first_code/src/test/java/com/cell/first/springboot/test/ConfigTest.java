package com.cell.first.springboot.test;

import com.cell.first.springboot.config.*;
import com.cell.first.springboot.service.UserServiceMulti;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigTest {
    @Autowired
    private AppConfig appConfig;

    @Autowired
    private UserServiceMulti userServiceMulti;

    @Autowired
    private ProFilesActiveConfig proFilesActiveConfig;

    @Autowired
    private EasyBeanConfig easyBeanConfig;

    @Autowired
    private EnableAppConfig enableAppConfig;

    @Autowired
    private CollectionConfig collectionConfig;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Test
    public void test() {
        appConfig.printInfo();
    }

    @Test
    public void testMergeConfig() {
        userServiceMulti.printInfo();
    }

    @Test
    public void testProFilesActiveConfig() {
        proFilesActiveConfig.print();
    }

    @Test
    public void testEasyBeanConfig() {
        System.out.println(easyBeanConfig);
    }

    @Test
    public void testEnableAppConfig() {
        enableAppConfig.print();
    }

    @Test
    public void testCollectionConfig() {
        System.out.println(collectionConfig);
    }

    @Test
    public void testSmsConfig() {
        smsConfig.print();
    }

    @Test
    public void testEnvironmentConfig() {
        environmentConfig.printActProfiles();
        environmentConfig.printComLineArguments();
        environmentConfig.printPropertySourceList();
        environmentConfig.printSysEnvVariables();
        environmentConfig.printSysProperties();
    }
}
