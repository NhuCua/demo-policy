package com.example.demo.controller;

import com.example.demo.db1.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/getUser")
    public List<User> getUser() {
        List<User> user = userService.findAllUser();
        return user;
    }

    @GetMapping("/getUser1")
    public List<User> getUser1() {
        List<User> user = userService.findAllUserPro();
        return user;
    }

}