package com.cardinity.taskmanager.service;

import com.cardinity.taskmanager.model.TaskStatus;
import com.cardinity.taskmanager.model.User;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.Task;

import java.util.Collection;

public interface TaskmanagerService {

    Iterable<Project> getAllProjects();

    Collection<Task> getAllTaskByProject(Long ProjectId);

    Collection<Task> getAllTaskByStatus(TaskStatus status);

    Collection<Task> getAllTaskByDuedate();

    Iterable<Project> getProjectById(Long ProjectId);

    Iterable<User> getAvailableUsers();

    void assignTask(Long projectDropDown, String taskDescription, String taskStartDate, String taskDueDate, String userList);

}
