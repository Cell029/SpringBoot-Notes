package com.cell.notes.web.service;

import com.cell.notes.web.bean.Account;

public interface AccountService {

    void transfer(String fromActNo, String toActNo, double money);
}
