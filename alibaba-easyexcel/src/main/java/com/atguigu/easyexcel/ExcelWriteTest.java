package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.atguigu.easyexcel.dto.ExcelStudentDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 包名：com.atguigu.easyexcel
 *
 * @author Fun
 * 日期2021-11-01 20:39
 */
public class ExcelWriteTest {
    @Test
    public void simpleWriteXlsx(){
        //需要提前新建目录
        String fileName = "d:/simpleWrite.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ExcelStudentDTO.class).sheet("模板1").doWrite(data());

        //辅助方法
    }private List<ExcelStudentDTO> data(){
        List<ExcelStudentDTO> list = new ArrayList<>();

        //算上标题，做多可写65536行
        //超出：java.lang.IllegalArgumentException: Invalid row number (65536) outside allowable range (0..65535)
        for (int i = 1; i <= 65536; i++) {
            ExcelStudentDTO data = new ExcelStudentDTO();
            data.setName("张三"+i);
            data.setSalary(10.0+i);
            data.setBirthday(new Date());
            list.add(data);
        }
        return list;
    }
}
