package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.services.TrainingApplicationService;
import pl.szymonwrobel.tms.services.TrainingService;
import pl.szymonwrobel.tms.services.UserService;
import java.util.List;

@Controller
public class FrontendController {
    private final UserService userService;
    private final TrainingService trainingService;
    private final TrainingApplicationService trainingApplicationService;

    public FrontendController(UserService userService, TrainingService trainingService, TrainingApplicationService trainingApplicationService) {
        this.userService = userService;
        this.trainingService = trainingService;
        this.trainingApplicationService = trainingApplicationService;
    }







/*    @PostMapping("/addtraineruser")
    public String postCreateTrainerUser(Model model, TrainerUserDTO trainerUserDTO) {
        userService.createTrainerUser(trainerUserDTO);
        return "redirect:/";
    }*/



/*    @GetMapping("/addstudentuser")
    public String createStudentUser(Model model){
        model.addAttribute("studentuserdto", new StudentUserDTO());
        return "addstudentuser";
    }*/

/*    @PostMapping("/addstudentuser")
    public String postCreateStudentUser(StudentUserDTO studentUserDTO){
        userService.createStudentUser(studentUserDTO);
        return "addstudentuser";
    }*/





}
