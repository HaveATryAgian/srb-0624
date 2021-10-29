package com.atguigu.srb.core.controller.admin;

import com.atguigu.srb.exception.BusinessException;
import com.atguigu.srb.result.ResponseEnum;
import org.springframework.util.Assert;

/**
 * 包名：com.atguigu.srb.core.controller.admin
 *
 * @author Fun
 * 日期2021-10-29 19:35
 */
public class Test {
    public static void main(String[] args) {
        Object object = new Object();
        object=null;
//        if (object == null){
//            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
//        }
        Assert.notNull(object,ResponseEnum.BORROW_AMOUNT_NULL_ERROR.getMessage());
    }
}
