package com.PratikC54.journalApp.controller;

import com.PratikC54.journalApp.Entity.User;
import com.PratikC54.journalApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicControlelr {
    @GetMapping("/health-check")
    public String HealthCheck(){
        return "OK";
    }

    @Autowired
    private UserServices userService;
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }
}
