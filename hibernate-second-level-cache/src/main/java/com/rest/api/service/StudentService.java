package com.rest.api.service;

import com.rest.api.dto.StudentDto;

import java.util.List;

public interface StudentService {

    public StudentDto getStudentById(String studentId);

    public StudentDto saveStudent(StudentDto studentDto);

    public List<StudentDto> getStudents();


}
