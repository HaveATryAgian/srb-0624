package com.atguigu.srb.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 包名：com.atguigu.srb.oss.service
 *
 * @author Fun
 * 日期2021-11-05 14:02
 */
public interface FileService {
    String upload(MultipartFile multipartFile, String module) throws IOException;

    void removeFile(String url);
}
