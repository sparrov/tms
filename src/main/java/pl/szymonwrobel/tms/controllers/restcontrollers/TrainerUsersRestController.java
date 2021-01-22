package pl.szymonwrobel.tms.controllers.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.services.UserService;

import java.util.List;

@RestController
public class TrainerUsersRestController {

    private final UserService userService;

    public TrainerUsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rest/trainerusers")
    public List<TrainerUserDTO> getAllTrainers() {
        return userService.getAllTrainerUsers();
    }
}
