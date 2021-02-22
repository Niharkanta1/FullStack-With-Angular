package com.training.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.backend.entity.Todo;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<Todo, Long>{

	@Query("Select t from Todo t where userName = ?1")
	List<Todo> findByUsername(String userName);

}
