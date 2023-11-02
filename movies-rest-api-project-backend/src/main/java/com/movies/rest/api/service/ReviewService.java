package com.movies.rest.api.service;

import com.movies.rest.api.dtos.ReviewDto;

public interface ReviewService {
    public ReviewDto createReview(String reviewBody, String imdbId);
}
