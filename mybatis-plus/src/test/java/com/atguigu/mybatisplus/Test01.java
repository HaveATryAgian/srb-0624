package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void test01(){
        Integer count = userMapper.selectCount(null);
        System.out.println(count);
    }

}
