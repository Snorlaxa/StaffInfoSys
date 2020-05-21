package snorlaxa.com.infosys.personnel.system.view.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import snorlaxa.com.infosys.personnel.base.Result;
import snorlaxa.com.infosys.personnel.security.SysUser;
import snorlaxa.com.infosys.personnel.system.po.UserPo;
import snorlaxa.com.infosys.personnel.system.service.UserService;
import snorlaxa.com.infosys.personnel.system.view.params.PasswordParam;
import snorlaxa.com.infosys.personnel.system.view.params.SignUpParam;
import snorlaxa.com.infosys.personnel.utils.Results;

import java.security.Principal;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/current-user", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value = "/check-login" , method = RequestMethod.GET)
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
        String id  = userService.insertUser(userPo);
        return null != id?Results.success(id):Results.fail("此用户名已被占用！");
    }

    @RequestMapping(value = "/edit-password",method = RequestMethod.POST)
    public Result editPassword(@RequestBody PasswordParam password){
        SysUser sysUser = userService.getUserByName(password.getUsername());
        if(!StringUtils.equals(sysUser.getPassword(),password.getPassword())){
            return Results.fail("密码不正确");
        }
        UserPo userPo = new UserPo();
        userPo.setId(sysUser.getId());
        userPo.setPassword(password.getNewPassword());
        userPo.setUsername(password.getUsername());
        userPo.setRole(sysUser.getRole());
        return Results.success(userService.upsertUser(userPo));
    }

}
