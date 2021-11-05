package com.atguigu.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.easyexcel.dto.ExcelStudentDTO;
import jdk.internal.instrumentation.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 * 包名：com.atguigu.easyexcel.listener
 *
 * @author Fun
 * 日期2021-11-01 20:51
 */
@Slf4j
public class ExcelStudentDTOListener extends AnalysisEventListener<ExcelStudentDTO> {
    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(ExcelStudentDTO data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }
}
