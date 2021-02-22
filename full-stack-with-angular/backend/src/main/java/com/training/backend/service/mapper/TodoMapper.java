package com.training.backend.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.training.backend.dto.ToDo;
import com.training.backend.entity.Todo;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TodoMapper extends EntityMapper<ToDo, Todo>{

	@Mapping(target="description", source="desc")
	@Mapping(target="isDone", source="isCompleted")
	@Mapping(target="user", ignore = true)
	public Todo toEntity(ToDo dto);

	@Mapping(target="desc", source="description")
	@Mapping(target="isCompleted", source="isDone")
	public ToDo toDto(Todo entity);

}
