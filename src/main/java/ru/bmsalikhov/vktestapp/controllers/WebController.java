package ru.bmsalikhov.vktestapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bmsalikhov.vktestapp.services.LogsService;

@Controller
public class WebController {

    private LogsService logsService;

    public WebController(LogsService logsService) {
        this.logsService = logsService;
    }


    @GetMapping("/usr")
    public String users() {
        return "users";
    }

    @GetMapping("/pos")
    public String posts() {
        return "posts";
    }

    @GetMapping("/alb")
    public String albums() {
        return "albums";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("logs", logsService.findAllLogs());
        return "index";
    }

}
