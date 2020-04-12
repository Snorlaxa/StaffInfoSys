package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.system.service.DepartmentService;
import snorlaxa.com.infosys.personnel.system.service.JobService;
import snorlaxa.com.infosys.personnel.system.service.StaffService;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffSelectParam;
import snorlaxa.com.infosys.personnel.system.view.vo.JobVo;
import snorlaxa.com.infosys.personnel.system.view.vo.StaffVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/7 9:11
 */
@Controller
public class StaffPageController {
    private final Integer DEFAULT_PAGE_SIZE = 10;

    private final Integer DEFUALT_PAGE = 1;

    @Autowired
    StaffService staffService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    JobService jobService;

    @RequestMapping(value = "/staff-single/{id}",method = RequestMethod.GET)
    public String staffSingle(@PathVariable String id, HttpServletRequest request){
        StaffPo staffPo = staffService.getStaffById(id);
        JobVo jobVo = jobService.getJobVoById(staffPo.getJobId());
        request.setAttribute("staff",staffPo);
        request.setAttribute("job",jobVo);
        return "staff-single";
    }

    @PostMapping("/staff-list/search-list")
    public String searchStaff(@ModelAttribute("selectParam") StaffSelectParam selectParam, ModelMap request){
        return staffList(selectParam,new PageParam(),request);
    }

    @RequestMapping(value = "/staff-list",method = RequestMethod.GET)
    public String staffList(StaffSelectParam selectParam, PageParam pageParam,ModelMap request){
        if(pageParam.getPageSize() == null){
            pageParam.setPageSize(DEFAULT_PAGE_SIZE);
        }

        if(pageParam.getPage()==null){
            pageParam.setPage(DEFUALT_PAGE);
        }
        List<DepartmentPo> res = departmentService.selectDepartments(null,null);
        request.put("departments",res);
        PageInfoList<StaffVo> pageInfo = staffService.getStaffVo(selectParam,pageParam);
        request.put("pageInfo",pageInfo);
        request.put("selectParam",selectParam);
        return "staff-list";
    }

    @RequestMapping(value = "/create-staff",method = RequestMethod.GET)
    public String createList(ModelMap request){
        StaffParam staffPo = new StaffParam();
        request.put("staffParam",staffPo);
        List<DepartmentPo> res = departmentService.selectDepartments(null,null);
        List<JobPo> jobs =jobService.getJobs(new JobSelectDto());
        request.put("departments",res);
        request.put("jobs",jobs);
        return "create-staff";
    }

    @PostMapping("/create-staff")
    public String createStaff(@ModelAttribute("staffParam") StaffParam staffParam,ModelMap request){
        staffParam.setResumePath("/");
        staffService.upsertStaff(staffParam);
        return  staffList(new StaffSelectParam(),new PageParam(),request);
    }

}