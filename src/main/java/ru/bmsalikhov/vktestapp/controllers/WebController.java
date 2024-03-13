package ru.bmsalikhov.vktestapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

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

}
