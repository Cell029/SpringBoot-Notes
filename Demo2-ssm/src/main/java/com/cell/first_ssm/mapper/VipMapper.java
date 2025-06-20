package com.cell.first_ssm.mapper;

import com.cell.first_ssm.model.Vip;

import java.util.List;

public interface VipMapper {
    int insert(Vip vip);

    int deleteById(Long id);

    int update(Vip vip);

    Vip selectById(Long id);

    List<Vip> selectAll();
}
