package com.cardinity.taskmanager.repository;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.Task;
import com.cardinity.taskmanager.model.TaskStatus;
import com.cardinity.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("FROM Task WHERE project =:projectId")
    List<Task> findAllByProject(@Param("projectId") Project project);

    @Query("FROM Task WHERE status =:status")
    List<Task> findAllByStatus(@Param("status") TaskStatus status);

    @Query("FROM Task WHERE taskEndDate < current_date ")
    List<Task> findAllByDuedate();

}
