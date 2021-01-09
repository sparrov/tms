package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.services.UserService;

@Controller
public class CreateUserController {

    private final UserService userService;

    public CreateUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adduser")
    public String createUser(Model model) {
        model.addAttribute("userdto", new UserDTO());
        return "adduser";
    }

    @PostMapping("/adduser")
    public String postCreateUser(Model model, UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/";
    }

}
