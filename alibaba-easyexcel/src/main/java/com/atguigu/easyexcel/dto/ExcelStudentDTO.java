package com.atguigu.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 包名：com.atguigu.easyexcel.dto
 *
 * @author Fun
 * 日期2021-11-01 20:38
 */
@Data
public class ExcelStudentDTO {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("生日")
    private Date birthday;

    @ExcelProperty("薪资")
    private Double salary;
}
