package pl.tgruza.webcategorizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    //View of main page
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public String error() {
        return "exceptions/wrongUrlException";
    }
}
