package com.cell.two_ssm.service;

import com.cell.two_ssm.bean.Person;

public interface PersonService {
    Person selectByPrimaryKey(Long id);
}
