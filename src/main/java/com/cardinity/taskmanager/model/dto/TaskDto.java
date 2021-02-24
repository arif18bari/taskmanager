package com.cardinity.taskmanager.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class TaskDto {
    private long taskId;
    private String taskDescription;
    private String status;
    private Date taskStartDate;
    private Date taskEndDate;
    private List<UserDto> userList;
    private ProjectDto project;
}
