package com.reactive.rest.api.project.service.impl;

import com.reactive.rest.api.project.dto.StudentDTO;
import com.reactive.rest.api.project.entity.Student;
import com.reactive.rest.api.project.exceptions.ResourceNotFoundException;
import com.reactive.rest.api.project.mappers.StudentMapper;
import com.reactive.rest.api.project.repository.StudentRepository;
import com.reactive.rest.api.project.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        return StudentMapper.INSTANCE.toDto(studentRepository.save(StudentMapper.INSTANCE.toEntity(studentDTO)));
    }

    @Override
    public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student does not found"));
        student.setCity(studentDTO.getCity());
        student.setName(studentDTO.getName());
        return StudentMapper.INSTANCE.toDto(studentRepository.save(student));
    }

    @Override
    public StudentDTO getStudent(String id) {
        return StudentMapper.INSTANCE.toDto(studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student does not found")))
                ;
    }

    @Override
    public List<StudentDTO> getStudents() {
        return StudentMapper.INSTANCE.toDtoList(studentRepository.findAll());
    }

    @Override
    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student does not found"));
        studentRepository.delete(student);
    }
}
