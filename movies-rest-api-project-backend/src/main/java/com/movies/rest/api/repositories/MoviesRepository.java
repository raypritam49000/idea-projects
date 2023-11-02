package com.movies.rest.api.repositories;
import com.movies.rest.api.entities.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findMovieByImdbId(String imdbId);
}
