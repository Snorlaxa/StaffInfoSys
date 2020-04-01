package snorlaxa.com.infosys.personnel.system.view.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.Result;
import snorlaxa.com.infosys.personnel.contants.BaseEnums;
import snorlaxa.com.infosys.personnel.system.service.ScoreService;
import snorlaxa.com.infosys.personnel.system.view.params.JobScoreParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffScoreParam;
import snorlaxa.com.infosys.personnel.utils.Results;

import javax.validation.Valid;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 15:14
 */
@RestController
@RequestMapping("/api/score")
@Api(value = "评分系统")
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @GetMapping("/job-score")
    @ApiOperation(value = "获取岗位技能需求")
    public Result getJobScore(String jobId){
        if(StringUtils.isEmpty(jobId)){
            return Results.fail("JobId 不能为空！");
        }
        return Results.successWithData(
                scoreService.getJobScore(jobId),
                BaseEnums.SUCCESS.desc(),
                BaseEnums.SUCCESS.code());
    }

    @GetMapping("/staff-score")
    @ApiOperation(value = "获取员工技能评分")
    public Result getStaffScore(String staffId){
        if(StringUtils.isEmpty(staffId)){
            return Results.fail("StaffID 不能为空！");
        }
        return Results.successWithData(
                scoreService.getStaffScore(staffId),
                BaseEnums.SUCCESS.desc(),
                BaseEnums.SUCCESS.code());
    }

    @PostMapping("/job-score")
    @ApiOperation(value = "新增岗位技能需求")
    public Result upsertJobScore(@RequestBody @Valid JobScoreParam jobScoreParam){
        scoreService.upsertJobScore(jobScoreParam.getJobId(),jobScoreParam.getAbilityId(),jobScoreParam.getScore());
        return Results.success();
    }
    @PostMapping("/staff-score")
    @ApiOperation(value = "新增员工技能评分")
    public Result upsertStaffScore(@RequestBody @Valid StaffScoreParam staffScoreParam){
        scoreService.upsertStaffScore(staffScoreParam.getStaffId(),staffScoreParam.getAbilityId(),staffScoreParam.getScore());
        return Results.success();
    }

    @DeleteMapping("/job-score")
    @ApiOperation(value = "删除岗位技能需求")
    public Result deleteJobScore(String jobId,String abilityId){
        if(StringUtils.isEmpty(jobId)&&StringUtils.isEmpty(abilityId)){
            return Results.fail("JobID 与 AbilityID不能同时为空！");
        }
        scoreService.delJobScore(jobId,abilityId);
        return Results.success();
    }

    @DeleteMapping("/staff-score")
    @ApiOperation(value = "删除员工技能评分")
    public Result deleteStaffScore(String staffId,String abilityId){
        if(StringUtils.isEmpty(staffId)&&StringUtils.isEmpty(abilityId)){
            return Results.fail("StaffID 与 AbilityID不能同时为空！");
        }
        scoreService.delStaffScore(staffId,abilityId);
        return Results.success();
    }
}