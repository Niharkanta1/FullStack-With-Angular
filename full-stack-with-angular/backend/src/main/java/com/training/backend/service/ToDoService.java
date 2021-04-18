package com.training.backend.service;

import java.util.List;

import com.training.backend.dto.ToDo;

public interface ToDoService {
    public List<ToDo> findAll(String userName);
    public ToDo deleteToDo(Long id);
    public ToDo findById(Long id);
    public ToDo saveToDo(ToDo toDo, Boolean isCompleted);
}
