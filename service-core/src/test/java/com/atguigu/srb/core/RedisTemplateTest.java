package com.atguigu.srb.core;

import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.entity.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


/**
 * 包名：com.atguigu.srb.core
 *
 * @author Fun
 * 日期2021-11-03 11:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    DictMapper dictMapper;

    @Test
    public void test01(){
        //Dict dict = dictMapper.selectById(1);
        redisTemplate.opsForValue().set("k1","zs");
        redisTemplate.opsForValue().set("k2","ls");
        redisTemplate.opsForHash().put("map1","mk1","mv1");
    }

    @Test
    public void test02(){
        Object name1 = redisTemplate.opsForValue().get("k1");
        Object name2 = redisTemplate.opsForValue().get("k2");
        Object name3 = redisTemplate.opsForHash().get("map1","mk1");
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
    }
}
