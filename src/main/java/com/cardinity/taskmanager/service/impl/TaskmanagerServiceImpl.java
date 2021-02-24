package com.cardinity.taskmanager.service.impl;


import com.cardinity.taskmanager.model.TaskStatus;
import com.cardinity.taskmanager.repository.UserRepository;
import com.cardinity.taskmanager.repository.ProjectRepository;
import com.cardinity.taskmanager.repository.TaskRepository;
import com.cardinity.taskmanager.model.User;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.Task;
import com.cardinity.taskmanager.service.IService;
import com.cardinity.taskmanager.service.TaskmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TaskmanagerServiceImpl implements TaskmanagerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Collection<Task> getAllTaskByProject(Long projectId) {
        List<Task> tasks = new ArrayList<>();
        Project project = projectRepository.getOne(projectId);
        return taskRepository.findAllByProject(project);
    }



    @Override
    public Collection<Task> getAllTaskByStatus(TaskStatus status) {
        List<Task> tasks = new ArrayList<>();

        return taskRepository.findAllByStatus(status);
    }

    @Override
    public Collection<Task> getAllTaskByDuedate() {
        return taskRepository.findAllByDuedate();
    }


    @Override
    public Iterable<User> getAvailableUsers() {
        Iterable<User> availableUsers = new ArrayList<User>();

        for (User usr : userRepository.findAll()) {
            System.out.println(usr.getEmployeeName());
            if (usr.getProject() == null) {
                ((ArrayList<User>) availableUsers).add(usr);
            }
        }
        return availableUsers;
    }

    @Override
    @Transactional
    public void assignTask(Long projectDropDown, String taskDescription, String taskStartDate, String taskDueDate, String userList) {
        Task newTask = new Task();
        Project project = projectRepository.findById(projectDropDown).orElseThrow(() -> new EntityNotFoundException("Not Found-->" + projectDropDown));
        List<User> usersList = new ArrayList<>();
        for (String userId : userList.split(",")) {
            User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new EntityNotFoundException("Not Found-->" + userId));
            user.setProject(project);
            user.setTask(newTask);
            usersList.add(user);
        }
        newTask.setTaskDescription(taskDescription);
        try {
            newTask.setTaskStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(taskStartDate));
            newTask.setTaskEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(taskDueDate));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        newTask.setProject(project);
       newTask.setUserList(usersList);

        taskRepository.save(newTask);
    }

    @Override
    public Iterable<Project> getProjectById(Long ProjectId) {
        List<Project> projects = new ArrayList<>();
        projects.add(projectRepository.findById(ProjectId).orElseThrow(() -> new EntityNotFoundException("Not Found-->" + ProjectId)));
        return projects;
    }

}
