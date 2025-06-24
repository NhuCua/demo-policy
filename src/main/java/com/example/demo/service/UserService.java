package com.example.demo.service;

import com.example.demo.db1.model.User;
import com.example.demo.db1.repository.UserRepository;
import com.example.demo.db2.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private ProductRepo productRepo;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public List<User> findAllUserPro() {
        return productRepo.findAll();
    }


}
