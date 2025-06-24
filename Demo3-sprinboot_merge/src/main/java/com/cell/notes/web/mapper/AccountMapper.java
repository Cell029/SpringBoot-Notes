package com.cell.notes.web.mapper;

import com.cell.notes.web.bean.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {

    Account selectByActNo(String actno);

    int update(Account account);

}