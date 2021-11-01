package com.atguigu.srb.core.controller.admin;

import com.atguigu.srb.exception.Assert;
import com.atguigu.srb.exception.BusinessException;
import com.atguigu.srb.result.R;
import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import com.atguigu.srb.core.service.IntegralGradeService;
import com.atguigu.srb.result.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 包名：com.atguigu.srb.core.controller.admin
 *
 * @author Fun
 * 日期2021-10-29 11:42
 */
@RestController
@RequestMapping("/admin/core")
@Api("srb后台系统")
@Slf4j
@CrossOrigin
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll(){
        log.info("info级别日志");
        log.warn("warn级别日志");
        log.error("error级别日志");
        List<IntegralGrade> list = integralGradeService.list();
        R r = R.ok().data("list", list);
        return r;
    }

    @ApiOperation("根据id删除积分等级")
    @DeleteMapping("/remove/{id}")
    public R removeById(@ApiParam("要删除的主键值") @PathVariable Long id){
        boolean b = integralGradeService.removeById(id);
        if(b){
            //return R.setResult(ResponseEnum.UPLOAD_ERROR);
            return R.ok().message("删除成功");
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(@ApiParam("积分等级对象") @RequestBody IntegralGrade integralGrade) throws Exception {
        BigDecimal borrowAmount = integralGrade.getBorrowAmount();
//        if (borrowAmount.longValue() <= 0){
//            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
//        }
        Assert.isTrue(borrowAmount.longValue() > 0,ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean b = integralGradeService.save(integralGrade);
        Assert.isTrue(b,ResponseEnum.ERROR);
            return R.ok().data("flag",b);
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(@ApiParam("积分等级对象") @RequestBody IntegralGrade integralGrade){
        boolean b = integralGradeService.updateById(integralGrade);
        Assert.isTrue(b,ResponseEnum.ERROR);
            return R.ok().message("更新成功");
    }

    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam("数据id") @PathVariable Integer id){
        IntegralGrade data = integralGradeService.getById(id);
           if (data != null){
               return R.ok().data("data",data);
           }else {
               return R.error().message("数据不存在");
           }
    }

    @ApiOperation("test")
    @GetMapping("/test")
    public R test(){
            return R.ok().data("test","后端的test!!!");
    }
}
