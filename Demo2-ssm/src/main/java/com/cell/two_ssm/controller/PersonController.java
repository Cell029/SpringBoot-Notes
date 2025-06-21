package com.cell.two_ssm.controller;

import com.cell.two_ssm.bean.Person;
import com.cell.two_ssm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person/{id}")
    public Person personDetailById(@PathVariable("id") Long id) {
        return personService.selectByPrimaryKey(id);
    }
}
