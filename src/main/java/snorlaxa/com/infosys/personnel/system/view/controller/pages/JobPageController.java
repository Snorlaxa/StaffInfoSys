package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.service.DepartmentService;
import snorlaxa.com.infosys.personnel.system.service.JobService;
import snorlaxa.com.infosys.personnel.system.service.UserService;
import snorlaxa.com.infosys.personnel.system.view.params.JobParam;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
import snorlaxa.com.infosys.personnel.system.view.vo.JobVo;
import snorlaxa.com.infosys.personnel.utils.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/7 9:11
 */
@Controller
public class JobPageController {
    private final Integer DEFAULT_PAGE_SIZE = 10;

    private final Integer DEFUALT_PAGE = 1;

    @Autowired
    DepartmentService departmentService;
    @Autowired
    JobService jobService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/job-list",method = RequestMethod.GET)
    public String jobList(JobSelectDto selectParam, PageParam pageParam, ModelMap request){
        if(pageParam.getPageSize() == null){
            pageParam.setPageSize(DEFAULT_PAGE_SIZE);
        }

        if(pageParam.getPage()==null){
            pageParam.setPage(DEFUALT_PAGE);
        }
        List<DepartmentPo> res = departmentService.selectDepartments(null,null);
        request.put("departments",res);
        PageInfoList<JobVo> pageInfo = jobService.getJobVos(selectParam,pageParam);
        request.put("pageInfo",pageInfo);
        request.put("selectParam",selectParam);
        String name = AuthUtil.getUserName();
        request.put("username", name);
        request.put("updateTime",userService.getUserByName(name).getUpdateTime());
        return "job-list";
    }


    @RequestMapping(value = "/job-single/{id}",method = RequestMethod.GET)
    public String jobSingle(@PathVariable String id, HttpServletRequest request){
        JobVo jobVo = jobService.getJobVoById(id);
        request.setAttribute("job",jobVo);
        String name = AuthUtil.getUserName();
        request.setAttribute("username", name);
        request.setAttribute("updateTime",userService.getUserByName(name).getUpdateTime());
        return "job-single";
    }

    @RequestMapping(value = "/job-single/{id}/edit",method = RequestMethod.GET)
    public String jobSingleEdit(@PathVariable String id, HttpServletRequest request){
        JobVo jobVo = jobService.getJobVoById(id);
        request.setAttribute("job",jobVo);
        return "job-edit";
    }

    @PostMapping("/job-list/search-list")
    public String searchJob(@ModelAttribute("selectParam") JobSelectDto selectParam, ModelMap request){
        String name = AuthUtil.getUserName();
        request.put("username", name);
        request.put("updateTime",userService.getUserByName(name).getUpdateTime());
        return jobList(selectParam,new PageParam(),request);
    }

    @RequestMapping(value = "/create-job",method = RequestMethod.GET)
    public String createList(ModelMap request){
        JobParam jobParam = new JobParam();
        request.put("jobParam",jobParam);
        String name = AuthUtil.getUserName();
        request.put("username", name);
        request.put("updateTime",userService.getUserByName(name).getUpdateTime());
        List<DepartmentPo> res = departmentService.selectDepartments(null,null);
        request.put("departments",res);
        return "create-job";
    }
    @PostMapping("/create-job")
    public String createJob(@ModelAttribute("jobParam") JobParam jobParam, ModelMap request){
        String name = AuthUtil.getUserName();
        request.put("username", name);
        request.put("updateTime",userService.getUserByName(name).getUpdateTime());
        jobParam.setCurrentNumber(0);
        jobService.upsertJob(jobParam);
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(DEFAULT_PAGE_SIZE);
        pageParam.setPage(DEFUALT_PAGE);
        List<DepartmentPo> res = departmentService.selectDepartments(null,null);
        request.put("departments",res);
        JobSelectDto selectParam = new JobSelectDto();
        PageInfoList<JobVo> pageInfo = jobService.getJobVos(selectParam,pageParam);
        request.put("pageInfo",pageInfo);
        request.put("selectParam",selectParam);
        return "redirect:job-list";
    }

}
