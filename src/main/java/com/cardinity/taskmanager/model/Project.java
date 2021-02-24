package com.cardinity.taskmanager.model;

/**
 * Created by ariful on 2/19/21.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue
    private Long projectId;

    private String projectName;
    @Transient
    @JsonIgnore
    @OneToMany(targetEntity = User.class, mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> userLists;
    @Transient
    @JsonIgnore
    @OneToMany(targetEntity = Task.class, mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Project(Long id, @NotNull String name) {
        this.projectId = id;
        this.projectName = name;
    }
}
