package com.movies.rest.api.controllers;

import com.movies.rest.api.dtos.ReviewDto;
import com.movies.rest.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{imdbId}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable("imdbId") String imdbId){
        return new ResponseEntity<ReviewDto>(reviewService.createReview(reviewDto.getBody(), imdbId),HttpStatus.CREATED);
    }
}
