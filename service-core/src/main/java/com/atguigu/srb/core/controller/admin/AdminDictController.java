package com.atguigu.srb.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.pojo.entity.dto.ExcelDictDTO;
import com.atguigu.srb.core.service.DictService;
import com.atguigu.srb.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 包名：com.atguigu.srb.core.controller.admin
 *
 * @author Fun
 * 日期2021-11-02 14:26
 */
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
@CrossOrigin
public class AdminDictController {
    @Autowired
    DictService dictService;

    @SneakyThrows
    @ApiOperation("import")
    @PostMapping("/import")
    public R importData(@RequestParam("file") MultipartFile multipartFile){
        InputStream inputStream = multipartFile.getInputStream();
        dictService.importData(inputStream);
        return R.ok().message("上传成功");
    }

    @SneakyThrows
    @ApiOperation("export")
    @GetMapping("/export")
    public void exportData(HttpServletResponse response){
        List<ExcelDictDTO> excelDictDTOS = dictService.listDictData();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 输出流
        EasyExcel.write(response.getOutputStream(),ExcelDictDTO.class).sheet("数据字典").doWrite(excelDictDTOS);
    }

    @SneakyThrows
    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{id}")
    public R listByParentId(@PathVariable("id") Long id){
        List<Dict> dictList = dictService.listByParentId(id);
        return R.ok().data("dictList",dictList);
    }
}
