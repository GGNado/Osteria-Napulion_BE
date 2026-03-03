package com.giggi.osterianapulion_be.repository;

import com.giggi.osterianapulion_be.entity.Role;
import com.giggi.osterianapulion_be.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);
}
