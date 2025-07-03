package com.example.demo.repository.service;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("serviceUserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

}