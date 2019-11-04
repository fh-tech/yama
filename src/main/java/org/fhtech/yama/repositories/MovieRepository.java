package org.fhtech.yama.repositories;

import org.fhtech.yama.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Iterable<Movie> findMoviesByTitleContains(String searchString);
}
