package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.services.TrainingService;

import java.util.List;

@Controller
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/trainings")
    public String getAllTrainings(Model model) {
        final List<TrainingDTO> allTrainings = trainingService.getAllTrainings();
        model.addAttribute("listOfAllTrainings", allTrainings);
        return "trainings";
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

    @GetMapping("/training/edit/{id}")
    public String editTraining(@PathVariable Long id, Model model) {
        final TrainingEntity trainingEntity = trainingService
                .trainingRepository
                .findById(id)
                .orElse(new TrainingEntity());
        model.addAttribute("trainingdto", trainingEntity);
        return "training";
    }

    @PostMapping("/training/update/{id}")
    public String postEditTraining(@PathVariable Long id, TrainingEntity trainingEntity) {
        trainingService.trainingRepository.saveAndFlush(trainingEntity.setId(id));
        return "redirect:/trainings";
    }

    @GetMapping("/trainings/{id}/delete")
    public String deleteTrainingById(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return "redirect:/trainings";
    }
}
