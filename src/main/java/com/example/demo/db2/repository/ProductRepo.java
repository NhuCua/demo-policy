package com.example.demo.db2.repository;

import com.example.demo.db1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<User, Integer> {

}