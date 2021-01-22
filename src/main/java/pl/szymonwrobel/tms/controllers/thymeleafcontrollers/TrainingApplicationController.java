package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
