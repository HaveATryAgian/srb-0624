package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.service.UserService;
import com.atguigu.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.mybatisplus
 *
 * @author Fun
 * 日期2021-10-23 14:20
 */
@SpringBootTest
public class Test03 {
    @Autowired
    UserService userService;

    @Test
    public void test01(){
        List<User> lists = userService.list();
        System.out.println(lists);
    }

    @Test
    public void test02(){
        List<User> lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("Tom"+i);
            user.setAge(20+i);
            lists.add(user);
        }
        boolean saveBatch = userService.saveBatch(lists);
        System.out.println(saveBatch);
    }
}
