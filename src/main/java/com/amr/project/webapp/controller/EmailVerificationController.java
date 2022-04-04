package com.amr.project.webapp.controller;

import com.amr.project.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class EmailVerificationController {
    private UserService userService;

    @GetMapping("/registrationConfirm")
    public String verifyEmail(@RequestParam(name = "code") String code) {
        return userService.activateUser(code);
    }
}
