package com.atguigu.srb.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.srb.sms.util.SmsProperties;
import com.google.common.base.Utf8;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 包名：com.atguigu.srb.sms
 *
 * @author Fun
 * 日期2021-11-04 10:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {

    @SneakyThrows
    @Test
    public void test01(){
        //System.out.println(SmsProperties.KEY_ID);
        //System.out.println(SmsProperties.KEY_SECRET);
        //System.out.println(SmsProperties.SIGN_NAME);

        // 1 profile公共参数
        DefaultProfile profile = DefaultProfile.getProfile(SmsProperties.REGION_Id, SmsProperties.KEY_ID, SmsProperties.KEY_SECRET);

        // 2 创建短信api客户端
        IAcsClient  iAcsClient = new DefaultAcsClient(profile);

        // 3 拼接请求参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");// 请求接口名
        request.putQueryParameter("RegionId", SmsProperties.REGION_Id);
        request.putQueryParameter("PhoneNumbers", "18025225919");
        request.putQueryParameter("SignName", URLDecoder.decode(SmsProperties.SIGN_NAME,"utf-8"));
        request.putQueryParameter("TemplateCode", "SMS_217425770");

        // 短信内容(验证码)
        Gson gson = new Gson();
        Map<String,String> param = new HashMap<>();
        param.put("code","8888");
        String json = gson.toJson(param);
        request.putQueryParameter("TemplateParam", json);

        // 4 发送请求，获得返回结果
        CommonResponse commonResponse = iAcsClient.getCommonResponse(request);

        // 5 解析返回结果
        int httpStatus = commonResponse.getHttpStatus();
        System.out.println("响应码："+httpStatus);


    }
}
