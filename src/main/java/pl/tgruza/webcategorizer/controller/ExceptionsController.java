package pl.tgruza.webcategorizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionsController {

    @GetMapping("/url_problem")
    public String urlException() {
        return "exceptions/wrongUrlException";
    }
}
