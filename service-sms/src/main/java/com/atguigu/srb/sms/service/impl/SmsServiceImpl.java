package com.atguigu.srb.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.srb.sms.service.SmsService;
import com.atguigu.srb.sms.util.SmsProperties;
import com.atguigu.srb.util.RandomUtils;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 包名：com.atguigu.srb.sms.service.impl
 *
 * @author Fun
 * 日期2021-11-04 11:44
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public void send(String mobile) {
        // 生成验证码
        String fourBitRandom = RandomUtils.getFourBitRandom();

        // 调用smsapi
        // 1 profile公共参数
        DefaultProfile profile = DefaultProfile.getProfile(SmsProperties.REGION_Id, SmsProperties.KEY_ID, SmsProperties.KEY_SECRET);

        // 2 创建短信api客户端
        IAcsClient iAcsClient = new DefaultAcsClient(profile);

        // 3 拼接请求参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");// 请求接口名
        request.putQueryParameter("RegionId", SmsProperties.REGION_Id);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", URLDecoder.decode(SmsProperties.SIGN_NAME,"utf-8"));
        request.putQueryParameter("TemplateCode", SmsProperties.TEMPLATE_CODE);

        // 短信内容(验证码)
        Gson gson = new Gson();
        Map<String,String> param = new HashMap<>();
        param.put("code",fourBitRandom);
        String json = gson.toJson(param);
        request.putQueryParameter("TemplateParam", json);

        // 4 发送请求，获得返回结果
        CommonResponse commonResponse = null;
        try {
            commonResponse = iAcsClient.getCommonResponse(request);
        }catch (Exception e){
            //记录日志
        }

        // 5 解析返回结果
        int httpStatus = commonResponse.getHttpStatus();
        System.out.println("响应码："+httpStatus);
        System.out.println("验证码："+fourBitRandom);


        //保存到redis
        redisTemplate.opsForValue().set("srb:sms:code:"+ mobile,fourBitRandom,5, TimeUnit.MINUTES);
    }
}
