package com.mongodb.rest.api.dtos;

import com.mongodb.rest.api.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String studentId;
    private String name;
    private String email;
    private String salary;
    private List<CourseDto> courses;
    private List<AddressDto> address;
}
