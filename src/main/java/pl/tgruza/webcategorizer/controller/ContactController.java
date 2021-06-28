package pl.tgruza.webcategorizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    //View of contact page
    @GetMapping("/contact")
    public String contact() {
        return "contact/contact";
    }
}
