package org.fhtech.yamaServer.repositories;

import org.fhtech.yamaServer.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Iterable<Movie> findMoviesByTitleContains(String searchString);
}
