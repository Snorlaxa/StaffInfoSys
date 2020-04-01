package snorlaxa.com.infosys.personnel.system.view.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.Result;
import snorlaxa.com.infosys.personnel.contants.BaseEnums;
import snorlaxa.com.infosys.personnel.system.service.DepartmentService;
import snorlaxa.com.infosys.personnel.system.view.params.DepartmentParam;
import snorlaxa.com.infosys.personnel.utils.Results;

import javax.validation.Valid;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/29 18:56
 */
@RestController
@Api(value = "部门信息")
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    @ApiOperation(value = "获取部门信息")
    public Result selectDepartments(String id,String name){
        return Results.successWithData(departmentService.selectDepartments(id,name),
                BaseEnums.SUCCESS.desc(),BaseEnums.SUCCESS.code());
    }

    @PutMapping("/")
    @ApiOperation(value = "新增或更新部门信息")
    public Result upsertDepartment(@RequestBody @Valid DepartmentParam departmentParam){
        String uuid = departmentService.upsertDepartment(departmentParam);
        return Results.successWithData(uuid,BaseEnums.OPERATION_SUCCESS.desc(),BaseEnums.OPERATION_SUCCESS.code());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "删除部门信息")
    public Result deleteDepartment(String id){
        if(StringUtils.isEmpty(id)){
            return Results.fail("部门id不能为空！");
        }
        departmentService.deleteDepartment(id);
        return Results.success(BaseEnums.OPERATION_SUCCESS.desc(),BaseEnums.OPERATION_SUCCESS.code());
    }
}
