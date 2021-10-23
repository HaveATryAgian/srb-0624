package com.atguigu.mybatisplus.dao.impl;

import com.atguigu.mybatisplus.dao.UserService;
import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 包名：com.atguigu.mybatisplus.dao.impl
 *
 * @author Fun
 * 日期2021-10-23 14:18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
