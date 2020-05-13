package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.service.DepartmentService;
import snorlaxa.com.infosys.personnel.system.service.JobService;
import snorlaxa.com.infosys.personnel.system.service.StaffService;
import snorlaxa.com.infosys.personnel.system.view.params.*;
import snorlaxa.com.infosys.personnel.system.view.vo.StaffVo;
import snorlaxa.com.infosys.personnel.utils.AuthUtil;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/7 9:11
 */
@Controller
public class DepartmentPageController {
    private final Integer DEFAULT_PAGE_SIZE = 10;

    private final Integer DEFUALT_PAGE = 1;

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/department-list/search-list")
    public String searchDepartment(@ModelAttribute("selectParam") DepartmentParam departmentParam, ModelMap request){
        return departmentList(departmentParam,new PageParam(),request);
    }

    @RequestMapping(value = "/department-list",method = RequestMethod.GET)
    public String departmentList(DepartmentParam departmentParam, PageParam pageParam, ModelMap request){
        if(pageParam.getPageSize() == null){
            pageParam.setPageSize(DEFAULT_PAGE_SIZE);
        }

        if(pageParam.getPage()==null){
            pageParam.setPage(DEFUALT_PAGE);
        }
        PageInfoList<DepartmentPo> pageInfo = departmentService.getDepartmentPo(departmentParam,pageParam);
        request.put("pageInfo",pageInfo);
        request.put("selectParam",departmentParam);
        request.put("username", AuthUtil.getUserName());
        return "department-list";
    }

    @RequestMapping(value = "/create-department",method = RequestMethod.GET)
    public String createDepartment(ModelMap request){
        DepartmentParam departmentParam = new DepartmentParam();
        request.put("departmentParam",departmentParam);
        request.put("username", AuthUtil.getUserName());
        return "create-department";
    }

    @PostMapping("/create-department")
    public String createDepartment(@ModelAttribute("departmentParam") DepartmentParam departmentParam, ModelMap request){
        PageParam pageParam =  new PageParam();
        departmentService.upsertDepartment(departmentParam);
        pageParam.setPageSize(DEFAULT_PAGE_SIZE);
        pageParam.setPage(DEFUALT_PAGE);
        PageInfoList<DepartmentPo> pageInfo = departmentService.getDepartmentPo(departmentParam,pageParam);
        request.put("pageInfo",pageInfo);
        request.put("selectParam",departmentParam);
        request.put("username", AuthUtil.getUserName());
        return "redirect:department-list";
    }

}
