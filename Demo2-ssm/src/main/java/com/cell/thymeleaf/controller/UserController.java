package com.cell.thymeleaf.controller;

import com.cell.thymeleaf.bean.User;
import com.cell.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "list";
    }
}
