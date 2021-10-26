package com.atguigu.mybatisplus.controller;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 包名：com.atguigu.mybatisplus.controller
 *
 * @author Fun
 * 日期2021-10-26 11:18
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> getUserList(){
        return userService.list();
    }
}
