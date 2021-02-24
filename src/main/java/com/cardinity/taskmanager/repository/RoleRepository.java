package com.cardinity.taskmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardinity.taskmanager.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
