package com.movies.rest.api.service;

import com.movies.rest.api.dtos.MovieDto;
import com.movies.rest.api.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public List<MovieDto> findAllMovies();

    MovieDto findMovieByImdbId(String imdbId);
}
