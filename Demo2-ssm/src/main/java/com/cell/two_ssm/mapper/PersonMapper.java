package com.cell.two_ssm.mapper;

import com.cell.two_ssm.bean.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}