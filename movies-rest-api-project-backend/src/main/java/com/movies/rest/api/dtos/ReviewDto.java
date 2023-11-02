package com.movies.rest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private ObjectId id;
    private String body;

    public ReviewDto(String body) {
        this.body = body;
    }
}
