package com.training.backend.rest;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.training.backend.dto.ToDo;
import com.training.backend.service.ToDoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ToDoResource {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/user/{userName}/todos")
    public List<ToDo> findAllToDos(@PathVariable String userName){
         List<ToDo> findAll = toDoService.findAll(userName);
         return findAll.stream().sorted(Comparator.comparingLong(ToDo::getId)).collect(Collectors.toList());
    }

    @GetMapping("/user/{userName}/todos/{id}")
    public ToDo findById(@PathVariable String userName, @PathVariable Long id){
        return toDoService.findById(id);
    }

    @DeleteMapping("/user/{userName}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable Long id){
        ToDo todo = toDoService.deleteToDo(id);
        if(Objects.nonNull(todo)){
            log.debug("Todo is not found with id {}", id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/user/{userName}/todos/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable String userName, @PathVariable int id, @RequestBody ToDo todo){
        ToDo result = toDoService.saveToDo(todo, false);
        if(Objects.isNull(result)){
            log.debug("Todo is not found with id {}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }
    
    @GetMapping("/user/{userName}/todos/{id}/complete")
    public ResponseEntity<ToDo> markAsCompleteTodo(@PathVariable String userName, @PathVariable Long id){
    	ToDo todo = toDoService.findById(id);
        if(Objects.isNull(todo)){
            log.debug("Todo is not found with id {}", id);
            return ResponseEntity.notFound().build();
        }
        ToDo result = toDoService.saveToDo(todo, true);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/user/{userName}/todos")
    public ResponseEntity<Void> saveTodo(@PathVariable String userName, @RequestBody ToDo todo){
    	todo.setUserName(userName);
        ToDo result = toDoService.saveToDo(todo, false);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
