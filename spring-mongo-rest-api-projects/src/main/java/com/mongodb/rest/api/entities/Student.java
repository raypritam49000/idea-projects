package com.mongodb.rest.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    private String studentId;
    private String name;
    private String email;
    private String salary;

    @DBRef
    List<Course> courses;

    @DocumentReference
    private List<Address> address;

    private List<StudentContacts> studentContacts;
}
