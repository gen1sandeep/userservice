package com.udemy.UserService.controller;

import com.udemy.UserService.entities.Course;
import com.udemy.UserService.entities.User;
import com.udemy.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/api/users")
    public User registerUser(@Valid @RequestBody User newUser){

        return service.createUser(newUser);
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser(@PathVariable("userId") int userId, @Valid @RequestBody User userTobeUpdated){

        userTobeUpdated.setId(userId);
        return service.updateUser(userTobeUpdated);
    }

    @GetMapping("/api/users")
        public List<User> getUsers(){
        return service.getUsers("");
    }

    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        service.deleteUser(userId);
    }


}
