package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 9:10
 */
@Controller
public class ErrorPageController {

    @GetMapping(value = "/error")
    public String error(){
        return "error";
    }

    @GetMapping(value = "/404")
    public String error404(){
        return "404";
    }
}
