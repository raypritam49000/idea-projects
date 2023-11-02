package com.reactive.rest.api.project.service;

import com.reactive.rest.api.project.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(String id, StudentDTO studentDTO);

    StudentDTO getStudent(String id);

    List<StudentDTO> getStudents();

    void deleteStudent(String id);

}
