package com.training.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    private Long id;
    private String desc;
    private String userName;
    private Date targetDate;
    private Boolean isCompleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id &&
                userName.equals(toDo.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
}
