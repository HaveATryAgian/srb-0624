package com.atguigu.srb.oss.controller;

import com.atguigu.srb.oss.service.FileService;
import com.atguigu.srb.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 包名：com.atguigu.srb.oss.controller
 *
 * @author Fun
 * 日期2021-11-05 14:00
 */
@Api(tags = "阿里云文件管理")
@RestController
@CrossOrigin
@RequestMapping("/api/oss/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(@ApiParam("文件") @RequestParam("file")MultipartFile multipartFile,
                    @ApiParam(value = "模块") @RequestParam("module") String module) throws IOException {
        String url = fileService.upload(multipartFile, module);
        return R.ok().message("文件上传成功").data("url", url);
    }


    @ApiOperation("删除文件")
    @DeleteMapping("/remove")
    public R remove(@ApiParam("url") @RequestParam("url")String url){
        fileService.removeFile(url);
        return R.ok().message("删除成功");
    }
}
