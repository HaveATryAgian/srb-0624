package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包名：com.atguigu
 *
 * @author Fun
 * 日期2021-10-22 19:40
 */
@SpringBootTest
public class Test01 {
    @Autowired
    UserMapper userMapper;


    @Test
    public void test0() {
        Page<User> page = new Page<>();
        page.setCurrent(2);
        page.setSize(3);
        Page<User> selectPage = userMapper.selectPage(page, null);
        System.out.println(selectPage);
    }

}
