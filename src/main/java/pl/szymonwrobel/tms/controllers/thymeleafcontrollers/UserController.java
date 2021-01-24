package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.exceptions.UserAlreadyExistAuthenticationException;
import pl.szymonwrobel.tms.services.UserService;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/trainerusers")
    public String getAllTrainerUsers(Model model) {
        model.addAttribute("traineruserdto", new TrainerUserDTO());
        final List<TrainerUserDTO> allTrainerUsers = userService.getAllTrainerUsers();
        model.addAttribute("listOfAllTrainerUsers", allTrainerUsers);
        model.addAttribute("objecttoadd", "Dodaj prowadzącego");
        return "trainerusers";
    }

    @GetMapping("/addtraineruser")
    public String createTrainerUser(Model model) {
        model.addAttribute("traineruserdto", new TrainerUserDTO());
        return "addtraineruser";
    }

    @PostMapping("/trainerusers")
    public String postCreateTrainerUser(Model model, TrainerUserDTO trainerUserDTO)
            throws UserAlreadyExistAuthenticationException {
        userService.createTrainerUser(trainerUserDTO);
        return "redirect:/trainerusers";
    }

    @GetMapping("/trainerusers/delete/{id}")
    public String deleteTrainerUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/trainerusers";
    }

    @GetMapping("/studentusers")
    public String getAllStudentUsers(Model model) {
        final List<StudentUserDTO> allStudentUsers = userService.getAllStudentUsers();
        model.addAttribute("listOfAllStudentUsers", allStudentUsers);
        model.addAttribute("studentuserdto", new StudentUserDTO());
        return "studentusers";
    }

    @PostMapping("/studentusers")
    public String postCreateStudentUser(StudentUserDTO studentUserDTO)
            throws UserAlreadyExistAuthenticationException {
        userService.createStudentUser(studentUserDTO);
        return "redirect:/studentusers";
    }

    @GetMapping("/studentusers/delete/{id}")
    public String deleteStudentUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/studentusers";
    }
}
