package com.amr.project.webapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
//Вывод представления
public class MainPageController {
    @GetMapping()
    public String returnMainPage () {
        return "/index";
    }
}
