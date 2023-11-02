package com.mongodb.rest.api.service;

import com.mongodb.rest.api.dtos.StudentDto;

public interface StudentService {
    public StudentDto createStudent(StudentDto studentDto, String addressId, String courseId,String contactId);
}
