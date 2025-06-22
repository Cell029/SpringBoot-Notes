package com.cell.web.service.impl;

import com.cell.web.bean.User;
import com.cell.web.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById() {
        return new User("jack", 30);
    }
}
