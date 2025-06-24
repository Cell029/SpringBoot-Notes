package com.cell.notes.web.controller;

import com.cell.notes.web.bean.People;
import com.cell.notes.web.bean.User;
import com.cell.notes.web.enums.CodeEnum;
import com.cell.notes.web.result.R;
import com.cell.notes.web.service.PeopleService;
import com.cell.notes.web.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping("/list/{pageNo}")
    public PageInfo<People> list(@PathVariable("pageNo") int pageNo) {
        // 1.设置当前页码和每页显示的记录条数
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        // 2.获取数据（PageHelper会自动给SQL语句添加limit）
        List<People> peopleList = peopleService.findAll();
        // 3.将分页数据封装到PageInfo
        PageInfo<People> peoplePageInfo = new PageInfo<>(peopleList);
        return peoplePageInfo;
    }

    @RequestMapping("/detail")
    public R<User> detail() {
        User user = new User("jackson", 30);
        // return R.OK();
        // return R.FAIL();
        return R.FAIL(CodeEnum.BAD_REQUEST);
        //return R.OK(user);
    }
}
