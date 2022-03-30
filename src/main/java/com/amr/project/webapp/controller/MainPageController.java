package com.amr.project.webapp.controller;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amr.project.converter.CycleAvoidingMappingContext;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
//Вывод представления
public class MainPageController {

    private final UserService userService;

    @GetMapping
    public String returnMainPage (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/index";
    }


    @GetMapping("/")
    public String newPage(@AuthenticationPrincipal User user1, Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("user1", user1); // текущий авторизированный пользователь
        return "ExperimentalNewPage";
    }

    @GetMapping("/login1FA")
    public String preloginPage(Model model) {
        return "login1FA";
    }

    @GetMapping("/login1FAQR")
    public String postPage(@RequestParam String email, Model model) throws UnsupportedEncodingException {
        User user = userService.getUserByEmail(email);
        if (user.isUsing2FA()) {
            model.addAttribute("qrcode", userService.generateQRUrl(user));
            return "qrcode";
        }
        return  "redirect:/login";
    }
}
