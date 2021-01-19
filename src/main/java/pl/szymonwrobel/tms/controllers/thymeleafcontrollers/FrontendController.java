package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
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
    public String deleteTrainerUser(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/trainerusers";
    }

    @GetMapping("/addstudentuser")
    public String createStudentUser(Model model){
        model.addAttribute("studentuserdto", new StudentUserDTO());
        return "addstudentuser";
    }

    @PostMapping("/addstudentuser")
    public String postCreateStudentUser(StudentUserDTO studentUserDTO){
        userService.createStudentUser(studentUserDTO);
        return "redirect:/";
    }

    @GetMapping("/studentusers/{id}/delete")
    public String deleteStudentUser(Model model, @PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/studentusers";
    }

    @GetMapping("/studentusers")
    public String getAllStudentUsers(Model model){
        final List<StudentUserDTO> allStudentUsers = userService.getAllStudentUsers();
        model.addAttribute("listOfAllStudentUsers", allStudentUsers);
        return "studentusers";
    }

    @GetMapping("/applyfortraining")
    public String applyForTraining(Model model){
        model.addAttribute("applicationdto", new TrainingApplicationDTO());
        final List<TrainingDTO> allTrainings = trainingService.getAllTrainings();
        model.addAttribute("trainings", allTrainings);
        return "applyfortraining";
    }

    @PostMapping("/applyfortraining")
    public String postApplyForTraining(TrainingApplicationDTO trainingApplicationDTO){
        trainingApplicationService.createApplicationForTraining(trainingApplicationDTO);
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

}
