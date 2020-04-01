package snorlaxa.com.infosys.personnel.system.view.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/1 20:38
 */
@Controller
public class IndexPageController {

    @GetMapping("/")
    public String index(){
        return "index.html";
    }

}
