package com.cell.first_ssm.mapper;

import com.cell.first_ssm.bean.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}