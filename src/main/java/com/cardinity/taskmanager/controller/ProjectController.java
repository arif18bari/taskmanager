package com.cardinity.taskmanager.controller;

import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.User;
import com.cardinity.taskmanager.model.dto.ProjectDto;
import com.cardinity.taskmanager.service.IService;
import com.cardinity.taskmanager.service.ProjectService;
import com.cardinity.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @PutMapping("/projects/{id}")
    public Project updateProject(@PathVariable(value = "id") Integer taskId,
                                 @RequestBody Project project) {
        return projectService.updateOrCompleteProject(project);
    }

    @GetMapping("/projects/{id}")
    public Optional<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public User addOrUpdateUser(@RequestBody User user) {
        return userService.addOrUpdateUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.addOrUpdateUser(user);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long userId) {
        return userService.getUserById(userId);
    }
}
