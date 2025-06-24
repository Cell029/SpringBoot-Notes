package com.cell.notes.web.controller;

import com.cell.notes.web.bean.Account;
import com.cell.notes.web.mapper.AccountMapper;
import com.cell.notes.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @PostMapping("/transfer")
    public String transfer(Model model, String fromActNo, String toActNo, Double money) {
        accountService.transfer(fromActNo, toActNo, money);
        List<Account> accounts = new ArrayList<>();
        Account account1 = accountMapper.selectByActNo(fromActNo);
        Account account2 = accountMapper.selectByActNo(toActNo);
        accounts.add(account1);
        accounts.add(account2);
        model.addAttribute("accounts", accounts);
        return "success";
    }
}
