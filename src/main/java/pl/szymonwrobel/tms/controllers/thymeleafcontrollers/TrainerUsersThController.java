package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.services.UserService;

import java.util.List;

@Controller
public class TrainerUsersThController {

    private final UserService userService;

    public TrainerUsersThController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/trainerusers")
    public String getAllTrainerUsers(Model model) {
        final List<UserDTO> allTrainerUsers = userService.getAllTrainerUsers();
        model.addAttribute("listOfAllTrainerUsers", allTrainerUsers);
        return "trainerusers";
    }

    @GetMapping("/trainerusers/{id}/delete")
    public String deleteUser(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/trainerusers";
    }
}
