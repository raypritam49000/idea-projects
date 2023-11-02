package com.movies.rest.api.service.impl;

import com.movies.rest.api.dtos.MovieDto;
import com.movies.rest.api.exceptions.ResourceNotFoundException;
import com.movies.rest.api.repositories.MoviesRepository;
import com.movies.rest.api.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MoviesRepository moviesRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setMoviesRepository(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MovieDto> findAllMovies() {
        return moviesRepository.findAll().stream().map(movie -> modelMapper.map(movie,MovieDto.class)).toList();
    }

    @Override
    public MovieDto findMovieByImdbId(String imdbId) {
        return modelMapper.map(moviesRepository.findMovieByImdbId(imdbId).orElseThrow(() -> new ResourceNotFoundException("Movie does not found with imdbId with : " + imdbId)),MovieDto.class);
    }

}
