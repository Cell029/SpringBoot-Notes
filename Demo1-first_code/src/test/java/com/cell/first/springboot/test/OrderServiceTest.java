package com.cell.first.springboot.test;

import com.cell.first.springboot.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        orderService.generate(10, "name");
        orderService.detail(10);
    }
}
