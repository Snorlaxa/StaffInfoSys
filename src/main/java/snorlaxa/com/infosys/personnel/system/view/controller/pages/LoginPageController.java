package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 9:10
 */
@Controller
public class LoginPageController {

    @GetMapping(value = "/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signupWithPost(){
        return "redicret:login";
    }


    @GetMapping(value = "/toLogin")
    public String login2(ModelMap request){
        request.put("msg","");
        return "login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(ModelMap request){
        request.put("msg","用户名或密码错误!");
        return "login";
    }

}
