package com.atguigu.srb.core.pojo.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 包名：com.atguigu.srb.core.pojo.entity.dto
 *
 * @author Fun
 * 日期2021-11-02 14:28
 */
@Data
public class ExcelDictDTO {
    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("上级id")
    private Long parentId;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("值")
    private Integer value;

    @ExcelProperty("编码")
    private String dictCode;
}
