package com.cell.web.controller;

import com.cell.web.bean.User;
import com.cell.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/detail")
    public User detail() {
        return userService.getUserById();
    }
}
