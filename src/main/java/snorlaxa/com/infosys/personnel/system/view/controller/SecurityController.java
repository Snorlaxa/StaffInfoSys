package snorlaxa.com.infosys.personnel.system.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.Result;
import snorlaxa.com.infosys.personnel.system.po.UserPo;
import snorlaxa.com.infosys.personnel.system.service.UserService;
import snorlaxa.com.infosys.personnel.system.view.params.SignUpParam;
import snorlaxa.com.infosys.personnel.utils.Results;

import java.security.Principal;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getCurrentUserName", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value = "/checkLogin" , method = RequestMethod.GET)
    @ResponseBody
    public String CheckLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //主体名，即登录用户名
        return  name;
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public Result signUp(@RequestBody SignUpParam signUpParam){
        UserPo userPo = new UserPo();
        userPo.setPassword(signUpParam.getPassword());
        userPo.setUsername(signUpParam.getName());
        userPo.setRole("USER");
        String id  = userService.upsertUser(userPo);
        return Results.success();
    }
}
