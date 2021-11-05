package com.atguigu.srb.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.srb.oss.util.OssProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 包名：com.atguigu
 *
 * @author Fun
 * 日期2021-11-05 11:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {

    @Test
    public void a(){
        String bucketName = OssProperties.BUCKET_NAME;
        String keyId = OssProperties.KEY_ID;
        String keySecret = OssProperties.KEY_SECRET;
        String endpoint = OssProperties.ENDPOINT;

        System.out.println(bucketName);
        System.out.println(keyId);
        System.out.println(keySecret);
        System.out.println(endpoint);

        // 创建OSSClient实例。
        OSS oss = new OSSClientBuilder().build(endpoint,keyId,keySecret);


        //判断bucket是否存在
        boolean b = oss.doesBucketExist(bucketName);
        System.out.println(b);
        if (!b){
            // 创建存储空间。
            oss.createBucket(bucketName);
        }

        // 关闭OSSClient。
        oss.shutdown();
    }
}
