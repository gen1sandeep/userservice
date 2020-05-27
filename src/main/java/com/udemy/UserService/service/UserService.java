package com.udemy.UserService.service;

import com.udemy.UserService.entities.User;
import com.udemy.UserService.exception.ValidationException;
import com.udemy.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(User userToBeCreated){
        // get e-mail id from request
        String emailId = userToBeCreated.getEmailId();
        // checking if a user is present in DB with give mail id
        User existingUser = repository.findFirstByEmailId(emailId);
        if(existingUser != null){
            throw new ValidationException("INVALID", "Mail id is duplicate", "EmailID", emailId);
        }
        return repository.save(userToBeCreated);
    }

    public User updateUser(User userToBeupdated){
       return repository.save(userToBeupdated);
    }

    public List<User> getUsers(String username) {
        return repository.findAllByFirstNameStartingWith(username);
    }

    public void deleteUser(int userId) {
        if(!repository.findById(userId).isPresent()) {
            throw new ValidationException("INVALID", "invalid user id", "id", Integer.toString(userId));
        }else
            repository.deleteById(userId);

    }
}
