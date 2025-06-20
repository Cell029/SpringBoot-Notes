package com.cell.first.springboot.test;

import com.cell.first.springboot.bean.User;
import com.cell.first.springboot.service.UserServiceMultiYaml;
//import com.cell.first.springboot.service.YamlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class YamlTest {
    /*@Autowired
    private YamlService yamlService;*/

    @Autowired
    private UserServiceMultiYaml userServiceMultiYaml;

    @Autowired
    private User user;

    /*@Test
    public void test() {
        yamlService.print();
    }*/

    @Test
    public void test2() {
        userServiceMultiYaml.print();
    }

    @Test
    public void test3() {
        System.out.println(user);
    }
}
