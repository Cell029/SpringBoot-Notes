package com.cell.first_ssm.test;

import com.cell.first_ssm.mapper.VipMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cell.first_ssm.model.Vip;

import java.util.List;

@SpringBootTest
public class MybatisTest {
    @Autowired
    private VipMapper vipMapper;

    @Test
    public void testSelectAll() {
        List<Vip> vips = vipMapper.selectAll();
        for (Vip vip : vips) {
            System.out.println(vip);
        }
    }

    @Test
    public void testSelectById() {
        Vip vip = vipMapper.selectById(1L);
        System.out.println(vip);
    }

    @Test
    public void testInsert() {
        Vip newVip = new Vip("杰克", "1234567892", "1999-11-10");
        vipMapper.insert(newVip);
    }

    @Test
    public void testUpdate() {
        Vip vip = vipMapper.selectById(3L);
        vip.setName("zhangsan");
        vipMapper.update(vip);
    }

    @Test
    public void testDelete() {
        vipMapper.deleteById(3L);
    }
}
