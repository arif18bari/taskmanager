package com.cardinity.taskmanager.model;

import lombok.*;

import javax.persistence.*;
/**
 * Created by ariful on 2/19/21.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String employeeName;


    private String email;


    private String mobile;


    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;
}
