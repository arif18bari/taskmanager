package com.cardinity.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cardinity.taskmanager.model.Task;
import com.cardinity.taskmanager.model.User;

import com.cardinity.taskmanager.repository.ProjectRepository;
import com.cardinity.taskmanager.repository.TaskRepository;
import com.cardinity.taskmanager.repository.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addOrUpdateUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        return user;
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);;
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


}
