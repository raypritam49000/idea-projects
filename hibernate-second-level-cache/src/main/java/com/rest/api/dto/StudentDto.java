package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String studentId;
    private String name;
    private String city;
    private String email;
    private String courseName;
    private String duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto studentDto = (StudentDto) o;
        return Objects.equals(studentId, studentDto.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}
