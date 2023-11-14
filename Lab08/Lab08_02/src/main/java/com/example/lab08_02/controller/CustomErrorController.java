package com.example.lab08_02.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String errorPage(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("errorMessage", "Not available");
        return "errorPage";
    }

}
