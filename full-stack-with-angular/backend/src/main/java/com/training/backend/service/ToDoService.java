package com.training.backend.service;

import com.training.backend.bean.ToDo;

import java.util.List;

public interface ToDoService {
    public List<ToDo> findAll(String userName);
    public ToDo deleteToDo(int id);
    public ToDo findById(int id);
    public ToDo saveToDo(ToDo toDo);
}
