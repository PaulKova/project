package com.amr.project.webapp.controller;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
//Вывод представления
public class MainPageController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String returnMainPage (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/index";
    }

    @GetMapping("/activate/{code}")
    public String activationUser (Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User has been activated");
        } else {
            model.addAttribute("message", "Activation false");
        }
        return "redirect:/";
    }
}

