package com.amr.project.webapp.controller;
import com.amr.project.model.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.mapstruct.example.mapper.CycleAvoidingMappingContext;

@Controller
@RequestMapping("/")
//Вывод представления
public class MainPageController {

    @GetMapping
    public String returnMainPage (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/index";
    }
}
