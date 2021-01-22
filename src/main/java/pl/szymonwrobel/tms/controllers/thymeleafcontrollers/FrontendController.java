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

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "index";
    }

    @GetMapping("/addtraineruser")
    public String createTrainerUser(Model model) {
        model.addAttribute("traineruserdto", new TrainerUserDTO());
        return "addtraineruser";
    }

/*    @PostMapping("/addtraineruser")
    public String postCreateTrainerUser(Model model, TrainerUserDTO trainerUserDTO) {
        userService.createTrainerUser(trainerUserDTO);
        return "redirect:/";
    }*/

    @GetMapping("/trainerusers")
    public String getAllTrainerUsers(Model model) {
        model.addAttribute("traineruserdto", new TrainerUserDTO());
        final List<TrainerUserDTO> allTrainerUsers = userService.getAllTrainerUsers();
        model.addAttribute("listOfAllTrainerUsers", allTrainerUsers);
        model.addAttribute("objecttoadd", "Dodaj prowadzącego");
        return "trainerusers";
    }

    //TODO: w jaki sposób weryfikować, czy dodawany user nie jest już w bazie i nie jest nadpisywany (adnotacja unique?)
    //TODO: validation B indi ngResult
    @PostMapping("/trainerusers")
    public String postCreateTrainerUser(Model model, TrainerUserDTO trainerUserDTO) {
        userService.createTrainerUser(trainerUserDTO);
        return "redirect:/trainerusers";
    }

    @GetMapping("/trainerusers/{id}/delete")
    public String deleteTrainerUser(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/trainerusers";
    }

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

    @GetMapping("/studentusers/{id}/delete")
    public String deleteStudentUser(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/studentusers";
    }

    @GetMapping("/studentusers")
    public String getAllStudentUsers(Model model) {
        final List<StudentUserDTO> allStudentUsers = userService.getAllStudentUsers();
        model.addAttribute("listOfAllStudentUsers", allStudentUsers);
        model.addAttribute("studentuserdto", new StudentUserDTO());
        return "studentusers";
    }

    @PostMapping("/studentusers")
    public String postCreateStudentUser(StudentUserDTO studentUserDTO) {
        userService.createStudentUser(studentUserDTO);
        return "redirect:/studentusers";
    }

    @GetMapping("/applyfortraining")
    public String applyForTraining(Model model) {
        model.addAttribute("applicationdto", new TrainingApplicationDTO());
        final List<TrainingDTO> allTrainings = trainingService.getAllTrainings();
        model.addAttribute("trainings", allTrainings);
        return "applyfortraining";
    }

    @PostMapping("/applyfortraining")
    public String postApplyForTraining(TrainingApplicationDTO trainingApplicationDTO) {
        trainingApplicationService.createApplicationForTraining(trainingApplicationDTO);
        return "redirect:/";
    }

    @GetMapping("/addtraining")
    public String createTraining(Model model) {
        model.addAttribute("trainingdto", new TrainingDTO());
        return "addtraining";
    }

    @PostMapping("/addtraining")
    public String postCreateTraining(Model model, TrainingDTO trainingDTO) {
        trainingService.createTraining(trainingDTO);
        return "redirect:/trainings";
    }

    @GetMapping("/trainings")
    public String getAllTrainings(Model model) {
        final List<TrainingDTO> allTrainings = trainingService.getAllTrainings();
        model.addAttribute("listOfAllTrainings", allTrainings);
        return "trainings";
    }

    @GetMapping("/trainings/{id}/delete")
    public String deleteTrainingById(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return "redirect:/trainings";
    }

}
