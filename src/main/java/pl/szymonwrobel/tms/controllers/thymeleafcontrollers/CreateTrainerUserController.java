package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.services.UserService;

@Controller
public class CreateTrainerUserController {

    private final UserService userService;

    public CreateTrainerUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addtraineruser")
    public String createTrainerUser(Model model) {
        model.addAttribute("userdto", new UserDTO());
        return "addtraineruser";
    }

    @PostMapping("/addtraineruser")
    public String postCreateTrainerUser(Model model, UserDTO userDTO) {
        userService.createTrainerUser(userDTO);
        return "redirect:/";
    }

}
