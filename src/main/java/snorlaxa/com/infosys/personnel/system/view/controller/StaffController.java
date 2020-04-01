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
    public Result getStaffs(StaffSelectParam staffSelectParam){
        if(staffSelectParam==null){
            return Results.fail(BaseEnums.FAILURE.desc(),BaseEnums.FAILURE.code());
        }
        return Results.successWithData(staffService.getAllStaff(staffSelectParam),
                BaseEnums.SUCCESS.desc(),BaseEnums.SUCCESS.code());
    }

    @PostMapping("/")
    @ApiOperation(value = "新增员工信息")
    public Result createStaff(@RequestBody @Valid StaffParam staffParam){
        String uuid = staffService.upsertStaff(staffParam);
        return Results.successWithData(uuid,BaseEnums.OPERATION_SUCCESS.desc(),BaseEnums.OPERATION_SUCCESS.code());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除员工信息")
    public Result deleteStaff(@PathVariable String id){
        if(StringUtils.isEmpty(id)){
            return Results.fail("id不能为空！");
        }
        staffService.delStaff(id);
        return Results.success(BaseEnums.OPERATION_SUCCESS.desc(),BaseEnums.OPERATION_SUCCESS.code());
    }
}
