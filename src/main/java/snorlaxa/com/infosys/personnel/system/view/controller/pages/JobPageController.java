package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/7 9:11
 */
@Controller
public class JobPageController {

    @RequestMapping(value = "/job-list",method = RequestMethod.GET)
    public String jobList(HttpServletRequest request){
        return "job-list";
    }

    @RequestMapping(value = "/job-single",method = RequestMethod.GET)
    public String companySingle(HttpServletRequest request){
        return "job-single";
    }
}
