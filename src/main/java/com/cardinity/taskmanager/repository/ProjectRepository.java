package com.cardinity.taskmanager.repository;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.dto.ProjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
