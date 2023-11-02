package com.mongodb.rest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private String courseId;
    private String courseName;
    private String coursePrice;
    private String duration;
}
