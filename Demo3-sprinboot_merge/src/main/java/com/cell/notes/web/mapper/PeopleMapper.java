package com.cell.notes.web.mapper;

import com.cell.notes.web.bean.People;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);

    List<People> selectAll();
}