package com.cell.notes.web.service.impl;

import com.cell.notes.web.bean.People;
import com.cell.notes.web.mapper.PeopleMapper;
import com.cell.notes.web.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("peopleService")
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public List<People> findAll() {
        return peopleMapper.selectAll();
    }
}
