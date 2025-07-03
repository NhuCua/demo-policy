package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/getUserPolicy")
    public List<User> getUserPolicy() {
        List<User> user = userService.findAllUser();
        return user;
    }

    @GetMapping("/getUserService")
    public List<User> getUserService() {
        List<User> user = userService.findAllUserPro();
        return user;
    }

    @GetMapping("/getUserCountPolicy")
    public  Integer getUserCountPolicy() {
        Integer count = userService.getUserCount();
        return count;
    }
    @GetMapping("/getUserCountService")
    public  Integer getUserCountService() {
        Integer count = userService.getUserCountService();
        return count;
    }

}