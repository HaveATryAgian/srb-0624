package com.atguigu.mybatisplus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包名：com.atguigu.mybatisplus.entity
 *
 * @author Fun
 * 日期2021-10-22 19:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;


}
