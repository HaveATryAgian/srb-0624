package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 包名：com.atguigu.mybatisplus
 *
 * @author Fun
 * 日期2021-10-23 14:20
 */
@SpringBootTest
public class Test02 {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    public void test01(){
        List<User> list = userMapper.selectAllByName("Tom");
        System.out.println(list);
    }

    @Test
    public void test02(){
        List<User> list = userService.selectAllByName("Tom");
        System.out.println(list);
    }
}
