package com.mongodb.rest.api.service.impl;

import com.mongodb.rest.api.dtos.StudentDto;
import com.mongodb.rest.api.entities.Address;
import com.mongodb.rest.api.entities.Course;
import com.mongodb.rest.api.entities.StudentContacts;
import com.mongodb.rest.api.exceptions.ResourceNotFoundException;
import com.mongodb.rest.api.repository.AddressRepository;
import com.mongodb.rest.api.repository.CourseRepository;
import com.mongodb.rest.api.repository.StudentContactRepository;
import com.mongodb.rest.api.repository.StudentRepository;
import com.mongodb.rest.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StudentContactRepository studentContactRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto, String addressId, String courseId, String contactId) {

        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found with given addressId : " + addressId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with given addressId : " + courseId));
        StudentContacts contact = studentContactRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with given addressId : " + courseId));

        return null;
    }
}
