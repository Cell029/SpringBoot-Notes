package com.cell.two_ssm.service;

import com.cell.two_ssm.bean.Person;
import com.cell.two_ssm.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonMapper personMapper;


    @Override
    public Person selectByPrimaryKey(Long id) {
        return personMapper.selectByPrimaryKey(id);
    }
}
