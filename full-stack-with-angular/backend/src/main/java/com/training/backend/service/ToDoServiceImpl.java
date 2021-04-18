package com.training.backend.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.training.backend.dto.ToDo;
import com.training.backend.entity.Todo;
import com.training.backend.entity.User;
import com.training.backend.repository.TodoRepository;
import com.training.backend.repository.UserRepository;
import com.training.backend.service.mapper.TodoMapper;

@Service
public class ToDoServiceImpl implements ToDoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoMapper todoMapper;

	@Override
	public List<ToDo> findAll(String userName) {
		List<Todo> todos = todoRepository.findByUsername(userName);
		return todos.stream().map(todo -> todoMapper.toDto(todo)).collect(Collectors.toList());
	}

	@Override
	public ToDo deleteToDo(Long id) {
		Todo todo = todoRepository.getOne(id);
		if(nonNull(todo)) {
			todoRepository.delete(todo);
		}
		else {
			return null;
		}
		return todoMapper.toDto(todo);
	}

	@Override
	public ToDo findById(Long id) {
		Todo todo = todoRepository.getOne(id);
		if(nonNull(todo)) {
			return todoMapper.toDto(todo);
		}
		return null;
	}

	@Override
	public ToDo saveToDo(ToDo toDo, Boolean isCompleted) {
		User user = userRepository.findByUsername(toDo.getUserName());
		Todo entity = todoMapper.toEntity(toDo);
		entity.setUser(user);
		entity.setIsDone(isCompleted);
		Todo savedTodo = todoRepository.save(entity);
		return todoMapper.toDto(savedTodo);
	}

}
