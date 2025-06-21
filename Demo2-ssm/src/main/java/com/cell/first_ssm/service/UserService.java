package com.cell.first_ssm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    public void createUser(String name) {
        log.info("创建用户：{}", name);

        if (name == null || name.isBlank()) {
            log.warn("用户名不能为空");
            return;
        }

        try {
            // 模拟业务逻辑
            log.debug("执行业务逻辑中...");
            int result = 10 / 1;
            log.info("用户创建成功！");
        } catch (Exception e) {
            log.error("创建用户时发生异常：{}", e.getMessage(), e);
        }
    }
}
