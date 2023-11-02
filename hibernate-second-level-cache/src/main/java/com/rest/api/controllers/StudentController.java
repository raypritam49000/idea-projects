package com.rest.api.controllers;

import com.rest.api.dto.StudentDto;
import com.rest.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.saveStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentDto>> getCities() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
}
