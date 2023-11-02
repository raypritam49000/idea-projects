package com.mongodb.rest.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    private String courseId;
    private String courseName;
    private String coursePrice;
    private String duration;
}
