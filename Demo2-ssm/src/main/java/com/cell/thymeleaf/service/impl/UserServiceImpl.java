package com.cell.thymeleaf.service.impl;

import com.cell.thymeleaf.bean.User;
import com.cell.thymeleaf.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        users.add(new User("jackson", 20, true, "my name is jackson", "BEIJING"));
        users.add(new User("lucy", 20, false, "my name is lucy", "SHANGHAI"));
        users.add(new User("tom", 20, true, "my name is tom", "NANJING"));
        users.add(new User("smith", 20, true, "my name is smith", "SICHUAN"));
        users.add(new User("ford", 20, true, "my name is ford", "WUHAN"));
        return users;
    }
}
