package pl.szymonwrobel.tms.controllers.thymeleafcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szymonwrobel.tms.services.LogFileReaderService;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class HomeController {

    private final LogFileReaderService logFileReaderService;

    public HomeController(LogFileReaderService logFileReaderService) {
        this.logFileReaderService = logFileReaderService;
    }

    @GetMapping("/")
    public String getHome(Model model) throws FileNotFoundException {
        List<String> listOfAllLogRows = logFileReaderService.readLogFile();
        model.addAttribute("listOfAllLogRows", listOfAllLogRows);
        return "index";
    }
}
