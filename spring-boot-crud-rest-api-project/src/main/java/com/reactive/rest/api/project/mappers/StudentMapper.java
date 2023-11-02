package com.reactive.rest.api.project.mappers;

import com.reactive.rest.api.project.dto.StudentDTO;
import com.reactive.rest.api.project.entity.Student;
import com.reactive.rest.api.project.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper extends BaseMapper<StudentDTO, Student> {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
}
