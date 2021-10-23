package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
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
    public void test01(){
        Integer count = userMapper.selectCount(null);
        System.out.println(count);
    }

    @Test
    public void test02(){
        User user = userMapper.selectById(1L);
        System.out.println("user:"+user);
        System.out.println("userrrr:"+user);
    }

    @Test
    public void test03(){
        User user = new User(6l,"张三",18,"zs@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("受影响行数："+insert);
    }

    @Test
    public void test04(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println("users："+users);
    }

    @Test
    public void test05(){
        Map map = new HashMap();
        map.put("name", "张三");
        map.put("age", "18");
        List<User> users = userMapper.selectByMap(map);
        System.out.println("users："+users);
    }

    @Test
    public void test06(){
        User user = new User();
        user.setId(6L);
        user.setAge(22);
        int update = userMapper.updateById(user);
        System.out.println("受影响行数："+update);
    }

    @Test
    public void test07(){
        int delete = userMapper.deleteById(6L);
        System.out.println("受影响行数："+delete);
    }
}
