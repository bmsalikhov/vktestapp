package ru.bmsalikhov.vktestapp.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bmsalikhov.vktestapp.models.Log;
import ru.bmsalikhov.vktestapp.services.LogsService;

@Controller
@RequestMapping("/error")
@AllArgsConstructor
public class CustomErrorController implements ErrorController {

    private final LogsService logsService;

    @GetMapping
    public String handleAccessDeniedError(HttpServletRequest request,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        String originalUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        logsService.saveLog(new Log(userDetails.getUsername(), originalUri, "access denied"));
        return "errorPage";
    }
}
