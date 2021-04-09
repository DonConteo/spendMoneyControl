package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String saveUser(String username, String password){
        userService.saveUser(username, password);
        return "redirect:/login";
    }
}
