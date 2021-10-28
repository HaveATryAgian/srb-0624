package com.atguigu.mybatisplus.service.impl;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名：com.atguigu.mybatisplus.service.impl
 *
 * @author Fun
 * 日期2021-10-23 20:48
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Override
    public List<User> selectAllByName(String name) {
        return baseMapper.selectAllByName(name);
    }
}
