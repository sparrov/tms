package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.services.UserService;

import java.util.List;

@Controller
public class FrontendController {
    private final UserService userService;

    public FrontendController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        return "index";
    }

    @GetMapping("/addtraineruser")
    public String createTrainerUser(Model model) {
        model.addAttribute("traineruserdto", new TrainerUserDTO());
        return "addtraineruser";
    }

    @PostMapping("/addtraineruser")
    public String postCreateTrainerUser(Model model, TrainerUserDTO trainerUserDTO) {
        userService.createTrainerUser(trainerUserDTO);
        return "redirect:/";
    }

    @GetMapping("/trainerusers")
    public String getAllTrainerUsers(Model model) {
        final List<TrainerUserDTO> allTrainerUsers = userService.getAllTrainerUsers();
        model.addAttribute("listOfAllTrainerUsers", allTrainerUsers);
        return "trainerusers";
    }

    @GetMapping("/trainerusers/{id}/delete")
    public String deleteUser(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/trainerusers";
    }

    @GetMapping("/registerstudentuser")
    public String registerStudentUser(Model model){
        model.addAttribute("studentuserdto", new StudentUserDTO());
        return "registerstudentuser";
    }

    @PostMapping("/registerstudentuser")
    public String postRegisterStudentUser(StudentUserDTO studentUserDTO){
        userService.registerStudentUser(studentUserDTO);
        return "redirect:/";
    }
}
