package com.training.backend.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.training.backend.dto.ToDo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ToDoServiceHardcodedImpl implements ToDoService{
    private static Long idCounter=0L;
    private static List<ToDo> toDos;

    static {
        toDos = new ArrayList<>();
        toDos.add(new ToDo(++idCounter, "Learn Java", "nihar", new Date(), false));
        toDos.add(new ToDo(++idCounter, "Learn SpringBoot", "nihar", new Date(), false));
        toDos.add(new ToDo(++idCounter, "Learn Angular", "nihar", new Date(), false));
    }
    @Override
    public List<ToDo> findAll(String userName) {
        return toDos;
    }

    @Override
    public ToDo deleteToDo(Long id) {
        log.debug("Deleting todo with id {}", id);
        ToDo todo = findById(id);
        if(Objects.nonNull(todo)){
            toDos.remove(todo);
        }
        return todo;
    }

    public ToDo findById(Long id) {
        for(ToDo todo: toDos){
            if(todo.getId() == id){
                return todo;
            }
        }
        return null;
    }

    @Override
    public ToDo saveToDo(ToDo toDo, Boolean isCompleted) {
        if(toDo.getId() == -1 || toDo.getId() == 0){
            toDo.setId(++idCounter);
            toDo.setIsCompleted(false);
            toDos.add(toDo);
            return toDo;
        }
        if(Objects.nonNull(deleteToDo(toDo.getId()))){
            toDos.add(toDo);
            return toDo;
        }
        return null;
    }
}
