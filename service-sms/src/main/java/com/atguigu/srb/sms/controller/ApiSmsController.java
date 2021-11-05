package com.atguigu.srb.sms.controller;

import com.atguigu.srb.result.R;
import com.atguigu.srb.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 包名：com.atguigu.srb.sms.controller
 *
 * @author Fun
 * 日期2021-11-04 11:35
 */
@RestController
@CrossOrigin
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
@Slf4j
public class ApiSmsController {
    @Autowired
    private SmsService smsService;

    @ApiOperation("获取验证码")
    @GetMapping("/sen/{mobile}")
    public R send(@PathVariable("mobile") String mobile){
        smsService.send(mobile);
        return R.ok().message("发送成功！");
    }
}
