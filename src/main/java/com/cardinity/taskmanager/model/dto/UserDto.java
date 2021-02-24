package com.cardinity.taskmanager.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String employeeName;
    private String email;
    private String mobile;
    private String password;
    private RoleDto role;
    private ProjectDto project;
    private TaskDto task;
}
