package com.cardinity.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.cardinity.taskmanager.model.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.Task;
import com.cardinity.taskmanager.model.User;

import com.cardinity.taskmanager.repository.ProjectRepository;
import com.cardinity.taskmanager.repository.TaskRepository;
import com.cardinity.taskmanager.repository.UserRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Project> getAllProjects() {

        List<Project> projectList = projectRepo.findAll();
        return projectList;
    }

    public Project addProject(Project project) {
        return projectRepo.save(project);
    }

    public Project updateOrCompleteProject(Project project) {
        return projectRepo.save(project);
    }

    public Optional<Project> getProjectById(Long projectId) {
        Optional<Project> project = projectRepo.findById(projectId);
        return project;
    }

    public ProjectRepository getProjectRepo() {
        return projectRepo;
    }

    public void setProjectRepo(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }


}
