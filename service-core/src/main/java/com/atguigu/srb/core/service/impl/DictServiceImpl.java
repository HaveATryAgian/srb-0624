package com.atguigu.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.srb.core.listener.ExcelDictDTOListener;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.entity.dto.ExcelDictDTO;
import com.atguigu.srb.core.service.DictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author fun
 * @since 2021-10-29
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    DictMapper dictMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importData(InputStream inputStream) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, ExcelDictDTO.class,new ExcelDictDTOListener(dictMapper)).sheet("数据字典").doRead();
    }

    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dicts = dictMapper.selectList(null);
        List<ExcelDictDTO> excelDictDTOS = new ArrayList<>();
        for (Dict dict : dicts) {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict,excelDictDTO);
            excelDictDTOS.add(excelDictDTO);
        }
        return excelDictDTOS;
    }

    @Override
    public List<Dict> listByParentId(Long id) {
        long start = System.currentTimeMillis();
        //查询redis
        List<Dict> dicts = (List<Dict>)redisTemplate.opsForValue().get("srb:core:dictList:" + id);// 查询redis中的dict集合,dict:id:list
        if(dicts == null){
            //查询db
            QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",id);
            dicts = dictMapper.selectList(queryWrapper);
            if (dicts != null){
                //同步redis
                redisTemplate.opsForValue().set("\"srb:core:dictList:\" + id",dicts);
            }
        }

        if(dicts != null) {
            for (Dict dict : dicts) {
                Long dictId = dict.getId();
                QueryWrapper<Dict> queryWrapperTest = new QueryWrapper<>();
                queryWrapperTest.eq("parent_id",dictId);
                Integer integer = dictMapper.selectCount(queryWrapperTest);
                if (integer > 0){
                    dict.setHasChildren(true);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间："+(end-start));

        return dicts;
    }
}
