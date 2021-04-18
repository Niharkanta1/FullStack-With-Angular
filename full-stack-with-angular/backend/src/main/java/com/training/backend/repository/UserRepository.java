package com.training.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.backend.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
