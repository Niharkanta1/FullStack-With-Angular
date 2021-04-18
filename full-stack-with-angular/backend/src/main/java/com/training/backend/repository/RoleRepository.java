package com.training.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
