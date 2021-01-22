package pl.szymonwrobel.tms.controllers.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

    @GetMapping("/home")
    public String getTmsHomeController(){
        return "Hello from TMS";
    }
}
