package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.Product;
import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.ProductMapper;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class Test04 {
    @Autowired
    ProductMapper productMapper;


    @Test
    public void test0() {
        //1、小李
        Product p1 = productMapper.selectById(1L);
        //2、小王
        Product p2 = productMapper.selectById(1L);

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice()+50);
        productMapper.updateById(p1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice()-30);
        productMapper.updateById(p2);

        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());


    }
}
