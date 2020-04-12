package snorlaxa.com.infosys.personnel.system.view.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.Result;
import snorlaxa.com.infosys.personnel.contants.BaseEnums;
import snorlaxa.com.infosys.personnel.system.po.AbilityPo;
import snorlaxa.com.infosys.personnel.system.service.AbilityService;
import snorlaxa.com.infosys.personnel.system.view.params.AbilityParam;
import snorlaxa.com.infosys.personnel.utils.Results;

import javax.validation.Valid;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/29 17:52
 */
@RestController
@RequestMapping("/api/ability")
@Api(value = "技能信息")
public class AbilityController {
    @Autowired
    AbilityService abilityService;
    @GetMapping("/")
    @ApiOperation(value = "获取技能信息")
    public Result getAbilitys(AbilityPo abilityPo){
        if(abilityPo==null){
            return Results.fail(BaseEnums.FAILURE.desc(),BaseEnums.FAILURE.code());
        }
        return Results.successWithData(abilityService.selectAbilitys(abilityPo),
                BaseEnums.SUCCESS.desc(),BaseEnums.SUCCESS.code());
    }

    @PutMapping("/")
    @ApiOperation(value = "新增或更改技能信息")
    public Result upsertAbility(@RequestBody @Valid AbilityParam abilityParam){
        String uuid = abilityService.upsertAbility(abilityParam);
        return Results.successWithData(uuid,BaseEnums.OPERATION_SUCCESS.desc(),BaseEnums.OPERATION_SUCCESS.code());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "删除技能信息")
    public Result deleteAbility(String id,String classify){
        if(StringUtils.isEmpty(id)&&StringUtils.isEmpty(classify)){
            return Results.fail("id和类别不能同时为空！");
        }
        abilityService.deleteAbility(id,classify);
        return Results.success(BaseEnums.OPERATION_SUCCESS.desc(),BaseEnums.OPERATION_SUCCESS.code());
    }


}
