package com.atguigu.mybatisplus.service;

import com.atguigu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 包名：com.atguigu.mybatisplus.dao
 *
 * @author Fun
 * 日期2021-10-23 14:17
 */
public interface UserService extends IService<User> {
    List<User> selectAllByName(String name);
}
