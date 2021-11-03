package com.atguigu.srb.core.mapper;

import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.pojo.entity.dto.ExcelDictDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author fun
 * @since 2021-10-29
 */
public interface DictMapper extends BaseMapper<Dict> {
    void insertBatch(@Param("excelDictDTOS") List<ExcelDictDTO> excelDictDTOS);

}
