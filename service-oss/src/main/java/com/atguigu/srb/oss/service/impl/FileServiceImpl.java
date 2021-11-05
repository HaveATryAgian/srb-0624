package com.atguigu.srb.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.srb.oss.service.FileService;
import com.atguigu.srb.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 包名：com.atguigu.srb.oss.service.impl
 *
 * @author Fun
 * 日期2021-11-05 14:03
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile multipartFile, String module) throws IOException {

        String time = new DateTime().toString("yyyy/MM/dd");
        String fileName = UUID.randomUUID().toString()+ "."+StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String key = module + "/"+time+"/"+fileName;

        OSS build = new OSSClientBuilder().build(OssProperties.ENDPOINT, OssProperties.KEY_ID, OssProperties.KEY_SECRET);

        //文件上传至阿里云
        build.putObject(OssProperties.BUCKET_NAME,key,multipartFile.getInputStream());

        // 关闭OSSClient。
        build.shutdown();

        return "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/" + key;
    }

    @Override
    public void removeFile(String url) {
        OSS build = new OSSClientBuilder().build(
                OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET);

        String host = "https://"+OssProperties.BUCKET_NAME+"."+OssProperties.ENDPOINT+"/";
        String fileName = url.substring(host.length());
        System.out.println(fileName);

        //删除文件
        build.deleteObject(OssProperties.BUCKET_NAME,fileName);

        //关闭OSSClient
        build.shutdown();
    }
}
