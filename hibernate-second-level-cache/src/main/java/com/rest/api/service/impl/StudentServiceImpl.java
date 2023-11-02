package com.rest.api.service.impl;

import com.rest.api.dto.StudentDto;
import com.rest.api.entity.Student;
import com.rest.api.repository.StudentRepository;
import com.rest.api.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Cacheable(value = "student", key = "#studentId")
    @Override
    public StudentDto getStudentById(String studentId) {
        return modelMapper.map(studentRepository.findById(studentId).get(), StudentDto.class);
    }

    @CacheEvict(value = "students", allEntries = true)
    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        return modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentDto.class);
    }

    @Cacheable(value = "students")
    @Override
    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }
}
