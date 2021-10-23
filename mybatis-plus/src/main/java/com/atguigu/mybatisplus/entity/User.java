package com.atguigu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 包名：com.atguigu.mybatisplus.entity
 *
 * @author Fun
 * 日期2021-10-22 19:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User {



    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "email")
    private String email;


}
