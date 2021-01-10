package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeThController {

    @GetMapping("/")
    public String getHomePage(Model model){
        return "index";
    }
}
