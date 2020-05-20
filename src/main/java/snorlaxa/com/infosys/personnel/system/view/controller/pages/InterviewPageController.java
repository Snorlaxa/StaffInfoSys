package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.system.service.StaffService;
import snorlaxa.com.infosys.personnel.system.service.UserService;
import snorlaxa.com.infosys.personnel.utils.AuthUtil;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/11 11:06
 */
@Controller
public class InterviewPageController {

    @Autowired
    StaffService staffService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/interview/{id}", method = RequestMethod.GET)
    public String interview(@PathVariable String id, ModelMap request) {
        StaffPo staffPo = staffService.getStaffById(id);
        request.put("staff", staffPo);
        String name = AuthUtil.getUserName();
        request.put("username", name);
        request.put("updateTime", userService.getUserByName(name).getUpdateTime());
        return "interview";
    }
}
