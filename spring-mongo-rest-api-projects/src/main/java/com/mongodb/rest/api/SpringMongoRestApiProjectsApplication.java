package com.mongodb.rest.api;

import com.mongodb.rest.api.entities.Address;
import com.mongodb.rest.api.entities.Course;
import com.mongodb.rest.api.entities.Student;
import com.mongodb.rest.api.entities.StudentContacts;
import com.mongodb.rest.api.exceptions.ResourceNotFoundException;
import com.mongodb.rest.api.repository.AddressRepository;
import com.mongodb.rest.api.repository.CourseRepository;
import com.mongodb.rest.api.repository.StudentContactRepository;
import com.mongodb.rest.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringMongoRestApiProjectsApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StudentContactRepository studentContactRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoRestApiProjectsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Course course = new Course();
        course.setCourseName("Java Framework");
        course.setDuration("6th Months");
        course.setCoursePrice("15000");

        courseRepository.save(course);

        Address address = new Address();
        address.setCity("Ropar");
        address.setCountry("India");
        address.setState("Punjab");

        addressRepository.save(address);

        StudentContacts studentContacts = new StudentContacts();
        studentContacts.setEmail("raypritam49000@gmail.com");
        studentContacts.setPhone("8699535682");

        studentContactRepository.save(studentContacts);

        Student student = new Student();
        student.setAddress(List.of(address));
        student.setCourses(List.of(course));
        student.setStudentContacts(List.of(studentContacts));
        student.setName("Pritam Ray");
        student.setSalary("40000");
        student.setEmail("ray@gmail.com");

        studentRepository.save(student);

        System.out.println(studentRepository.findAll());

        System.out.println(studentRepository.findById("1").orElseThrow(() -> new ResourceNotFoundException("Student not found")));

    }
}
