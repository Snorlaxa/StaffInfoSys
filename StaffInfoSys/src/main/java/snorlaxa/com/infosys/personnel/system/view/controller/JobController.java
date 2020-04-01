package snorlaxa.com.infosys.personnel.system.view.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.Result;
import snorlaxa.com.infosys.personnel.contants.BaseEnums;
import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.service.JobService;
import snorlaxa.com.infosys.personnel.system.view.params.JobParam;
import snorlaxa.com.infosys.personnel.utils.Results;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/29 9:26
 */
@RestController
@Api(value = "岗位信息")
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("/")
    @ApiOperation(value = "获取岗位信息")
    public Result getJobs(JobSelectDto jobSelectDto){
        if(jobSelectDto==null){
            return Results.fail(BaseEnums.FAILURE.desc(),BaseEnums.FAILURE.code());
        }
        List<JobPo> result = jobService.getJobs(jobSelectDto);
        return Results.successWithData(result, BaseEnums.SUCCESS.desc(),BaseEnums.SUCCESS.code());
    }

    @PutMapping("/")
    @ApiOperation(value = "新增或更新岗位")
    public Result upsertJob(@RequestBody @Valid JobParam jobParam){
        String uuid = jobService.upsertJob(jobParam);
        return Results.successWithData(uuid,BaseEnums.SUCCESS.desc(),BaseEnums.SUCCESS.code());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除岗位信息")
    public Result deleteJob(@PathVariable String id){
        if(StringUtils.isEmpty(id)){
            return Results.fail("id不能为空！");
        }
        jobService.delJob(id);
        return Results.success();
    }

}
