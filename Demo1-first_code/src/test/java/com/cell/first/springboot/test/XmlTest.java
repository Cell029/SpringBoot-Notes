package com.cell.first.springboot.test;

import com.cell.first.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XmlTest {
    @Autowired
    private Person person;
    @Test
    void testPerson(){
        System.out.println(person);
    }
}
