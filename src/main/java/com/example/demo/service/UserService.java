package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.server.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private com.example.demo.repository.service.UserRepository sericeUserRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public List<User> findAllUserPro() {
        return sericeUserRepository.findAll();
    }


}
