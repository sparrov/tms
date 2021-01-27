package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
import pl.szymonwrobel.tms.services.TrainingApplicationService;
import pl.szymonwrobel.tms.services.TrainingService;

import java.util.List;

@Controller
public class TrainingApplicationController {

    private final TrainingService trainingService;
    private final TrainingApplicationService trainingApplicationService;

    public TrainingApplicationController(TrainingService trainingService,
                                         TrainingApplicationService trainingApplicationService) {
        this.trainingService = trainingService;
        this.trainingApplicationService = trainingApplicationService;
    }

    @GetMapping("/applyfortraining")
    public String getApplyForTrainingToCreate(Model model) {
        model.addAttribute("trainingApplicationDto", new TrainingApplicationDTO());
        final List<TrainingDTO> allTrainings = trainingService.getAllTrainings();
        model.addAttribute("listOfAllTrainings", allTrainings);
        return "applyfortraining";
    }

    @PostMapping("/applyfortraining")
    public String postApplyForTrainingToCreate(TrainingApplicationDTO trainingApplicationDTO) {
        trainingApplicationService.createApplicationForTraining(trainingApplicationDTO);
        return "redirect:/";
    }

    @GetMapping("/applications")
    public String getAllTrainingApplicationsToRead(Model model) {
        final List<TrainingApplicationDTO> allTrainingApplications = trainingApplicationService
                .getAllTrainingApplications();
        model.addAttribute("listOfAllTrainingApplications", allTrainingApplications);
        return "applications";
    }

    @GetMapping("/applications/update/{id}")
    public String getTrainingApplicationToUpdate(@PathVariable Long id) {
        trainingApplicationService.updateTrainingApplication(id);
        return "redirect:/applications";
    }

    @GetMapping("/applications/delete/{id}")
    public String getTrainingApplicationToDelete(@PathVariable Long id) {
        trainingApplicationService.deleteTrainingApplication(id);
        return "redirect:/applications";
    }
}