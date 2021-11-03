package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.atguigu.easyexcel.dto.ExcelStudentDTO;
import com.atguigu.easyexcel.listener.ExcelStudentDTOListener;
import org.junit.Test;

/**
 * 包名：com.atguigu.easyexcel
 *
 * @author Fun
 * 日期2021-11-01 20:54
 */
public class ExcelReadTest {
    /**
     * 最简单的读
     */
    @Test
    public void simpleReadXlsx(){
        String fileName = "d:/simpleWrite.xlsx";
        // 这里默认读取第一个sheet
        EasyExcel.read(fileName, ExcelStudentDTO.class,new ExcelStudentDTOListener()).excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }
}
