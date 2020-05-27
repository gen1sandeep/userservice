package com.udemy.UserService.repository;

import com.udemy.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>{

    public User findFirstByEmailId(String emailId);
    public List<User> findAllByFirstNameStartingWith(String userName);
}
