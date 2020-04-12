package snorlaxa.com.infosys.personnel.system.view.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.Result;
import snorlaxa.com.infosys.personnel.contants.BaseEnums;
import snorlaxa.com.infosys.personnel.system.service.StaffService;
import snorlaxa.com.infosys.personnel.system.view.params.StaffParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffSelectParam;
import snorlaxa.com.infosys.personnel.utils.Results;
import snorlaxa.com.infosys.personnel.utils.UUIDUtil;

import javax.validation.Valid;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/17 20:22
 */
@Api(value = "Staff服务")
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    @GetMapping("/")
    @ApiOperation(value = "获取员工信息")
    public Result getStaffs(StaffSelectParam staffSelectParam) {
        if (staffSelectParam == null) {
            return Results.fail(BaseEnums.FAILURE.desc(), BaseEnums.FAILURE.code());
        }
        return Results.successWithData(staffService.getAllStaff(staffSelectParam),
                BaseEnums.SUCCESS.desc(), BaseEnums.SUCCESS.code());
    }

    @PutMapping("/createMultipleStaffs")
    @ApiOperation(value = "生成大量员工信息")
    public Result createStaffs(Integer number, String jobId) {
        for (int i = 0; i < number; i++) {
            StaffParam selectParam = new StaffParam();
            selectParam.setAge(20 + i);
            selectParam.setName("测试人员-" + UUIDUtil.getUUID());
            selectParam.setCity("上海");
            selectParam.setDegree(i % 5);
            selectParam.setJobId(jobId);
            selectParam.setWorkingYears(i % 5);
            selectParam.setInterviewTimes(1);
            selectParam.setAddress("上海市宝山区大场镇");
            selectParam.setEmail("1231313@163.com");
            selectParam.setPhone("12313241341");
            selectParam.setEduExp("2010至2014：上海大学，计算机科学与技术专业，本科\n" +
                    "2014至2016：上海大学，计算机专业研究生\n");
            selectParam.setEmployStatus(1);
            selectParam.setGender(i % 2);
            selectParam.setInterviewScore((i + 17) * 35 % 100);
            selectParam.setProjectExp("1、3D双人联网小游戏Demo，Unity项目，使用简单的shader实现了透明光圈、大量向日葵摇摆动画、水面、泡泡、云朵运动。基于photon网络引擎实现创建、加入退出房间等操作，同步玩家动作。\n" +
                    "2、第12届中国大学生计算机设计大赛二等奖，上海市大学生计算机设计大赛二等奖。网络安全漏洞扫描系统,全栈Web项目。漏洞扫描基于Python多线程和开源漏扫插件，后端开发使用Flask框架，前端数据可视化基于原生css和echarts框架；\n" +
                    "第11届全国大学生计算机设计大赛三等奖。C2C二手书籍交易网站，Web项目。本人负责MySql数据库设计；");
            selectParam.setJobExp("2016至2018：滴滴打车，服务端java开发初级工程师，参与滴滴聚合支付核心系统技术框架设计和实现，基础组件建设，核心系统高可用能力建设\n" +
                    "2018至2020：苏宁易购，Java高级技术经理，负责苏宁易购供应商以及商户相关系统开发，涉及商家登录，门户，营促销，商品，订单，库存，运费，时效等业务模块\n");
            selectParam.setProbationScore(0);
            selectParam.setResumePath("/path");
            selectParam.setSpeciality("1.三年以上JAVA服务端开发的经验，JAVA基础扎实，熟悉io、多线程、JVM、集合等基础框架；\n" +
                    "2.熟悉互联网常用技术和中间件：分库分表技术、分布式框架、消息中间件；\n" +
                    "3.精通框架模式，IOC、AOP、MVC等框架原理及实现\n" +
                    "4.深入理解Spring、dubbo、netty等框架的设计思想和实现方式，精通分布式、多线程等高性能框架相关开发技术；\n");
            selectParam.setSchool("上海大学");
            selectParam.setInterviewComments("1.熟悉支付系统架构,会计系统架构\n" +
                    "2.有开源项目开发经验");
            selectParam.setInterviewers("张大大，王大大，李大大");
            staffService.upsertStaff(selectParam);
        }
        return Results.success();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "通过id获取员工信息")
    public Result getStaffById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Results.fail(BaseEnums.FAILURE.desc(), BaseEnums.FAILURE.code());
        }
        return Results.successWithData(staffService.getStaffById(id),
                BaseEnums.SUCCESS.desc(), BaseEnums.SUCCESS.code());
    }

    @GetMapping("/staffvo")
    @ApiOperation(value = "显示少量staff信息")
    public Result getStaffVos(StaffSelectParam selectParam) {
        if (selectParam == null) {
            return Results.fail(BaseEnums.FAILURE.desc(), BaseEnums.FAILURE.code());
        }
        return Results.successWithData(staffService.getStaffVo(selectParam),
                BaseEnums.SUCCESS.desc(), BaseEnums.SUCCESS.code());
    }

    @PostMapping("/")
    @ApiOperation(value = "新增员工信息")
    public Result createStaff(@RequestBody @Valid StaffParam staffParam) {
        String uuid = staffService.upsertStaff(staffParam);
        return Results.successWithData(uuid, BaseEnums.OPERATION_SUCCESS.desc(), BaseEnums.OPERATION_SUCCESS.code());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除员工信息")
    public Result deleteStaff(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Results.fail("id不能为空！");
        }
        staffService.delStaff(id);
        return Results.success(BaseEnums.OPERATION_SUCCESS.desc(), BaseEnums.OPERATION_SUCCESS.code());
    }
}
