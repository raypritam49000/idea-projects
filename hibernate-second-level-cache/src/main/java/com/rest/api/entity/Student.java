package com.rest.api.entity;

import com.rest.api.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
        Student studentDto = (Student) o;
        return Objects.equals(studentId, studentDto.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}
