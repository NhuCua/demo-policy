package com.example.demo.repository.service;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("serviceUserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    //Call function
    @Query(value = "select public.get_user_count()", nativeQuery = true)
    Integer getUserCount();
}