package org.fhtech.yamaServer.repositories;

import org.fhtech.yamaServer.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findMoviesByTitleContains(String searchString);
}
