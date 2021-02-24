package com.cardinity.taskmanager.model;

/**
 * Created by ariful on 2/19/21.
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {
    @Id
    @GeneratedValue
    private Long taskId;

    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskStartDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskEndDate;
    @Transient
    @OneToMany(targetEntity = User.class, mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> userList;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

   public Task(Long taskId, String taskDescription,TaskStatus status,Date taskStartDate, Date taskEndDate) {
       this.taskId = taskId;
       this.taskDescription = taskDescription;
       this.status = status;
       this.taskStartDate= taskStartDate;
       this.taskEndDate=taskEndDate;
   }
}
