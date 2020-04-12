package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dto.StaffSelectDto;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.system.service.DepartmentService;
import snorlaxa.com.infosys.personnel.system.service.StaffService;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffSelectParam;
import snorlaxa.com.infosys.personnel.system.view.vo.StaffVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Author: snorlaxa
 * @Date: 2020/4/1 20:38
 */
@Controller
public class IndexPageController {

    private final Integer STAFF_NUMBER_LIMIT = 5;


    @Autowired
    DepartmentService departmentService;

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String defaultIindex(ModelMap request){
        List<DepartmentPo> res = departmentService.selectDepartments(null,null);
        request.put("departments",res);
        StaffSelectParam staffSelectParam = new StaffSelectParam();
        staffSelectParam.setLimit(STAFF_NUMBER_LIMIT);
        List<StaffVo> staffVos = staffService.getStaffVo(staffSelectParam);
        request.put("staffs",staffVos);
        request.put("selectParam",staffSelectParam);
        return "index";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(ModelMap request){
        return defaultIindex(request);
    }





}
