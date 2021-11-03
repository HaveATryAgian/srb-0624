package com.atguigu.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.entity.dto.ExcelDictDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.srb.core.listener
 *
 * @author Fun
 * 日期2021-11-02 14:28
 */
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {
    private static int BATCH_COUNT=5;
    List<ExcelDictDTO> excelDictDTOS = new ArrayList<>();


    DictMapper dictMapper;

    public ExcelDictDTOListener(DictMapper dictMapper){
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        System.out.println("读到一行数据："+excelDictDTO.getId());
        excelDictDTOS.add(excelDictDTO);
        BATCH_COUNT--;
        if (BATCH_COUNT==0){
            // 满5条数据，插入数据库
            System.out.println("插入数据库:"+excelDictDTOS);
            dictMapper.insertBatch(excelDictDTOS);
            excelDictDTOS.clear();
            BATCH_COUNT=5;
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
          System.out.println("读取结束");
          System.out.println("插入数据库:"+excelDictDTOS);
          dictMapper.insertBatch(excelDictDTOS);
    }
}
