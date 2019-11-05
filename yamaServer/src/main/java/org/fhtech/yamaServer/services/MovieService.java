package org.fhtech.yamaServer.services;

import org.fhtech.yamaServer.domain.*;
import org.fhtech.yamaServer.repositories.ActorRepository;
import org.fhtech.yamaServer.repositories.MovieRepository;
import org.fhtech.yamaServer.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class MovieService {

    private MovieRepository movieRepository;
    private StudioRepository studioRepository;
    private ActorRepository actorRepository;

    public MovieService(@Autowired MovieRepository movieRepository,
                        @Autowired ActorRepository actorRepository,
                        @Autowired StudioRepository studioRepository) {
        this.movieRepository = movieRepository;
        this.studioRepository = studioRepository;
        this.actorRepository = actorRepository;
    }

    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie save(NewMovie newMovie) {
        Movie movie = new Movie(
                null,
                newMovie.getName(),
                newMovie.getDescription(),
                newMovie.getDuration(),
                newMovie.getReleaseYear(),
                (List<Actor>) getAllActorsForIds(newMovie.getActors()),
                findStudioById(newMovie.getStudioId()),
                newMovie.getGenre()
        );
        return movieRepository.save(movie);
    }

//    @Transactional makes it atomic so if one fails rolls back all (save all is already atomic!)
    public Iterable<Movie> saveAll(Iterable<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    public Movie findById(long id) {
        return movieRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }

    public void deleteById(long id) {
        if(!movieRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        movieRepository.deleteById(id);
    }

    public Movie update(long id, UpdateMovie updateMovie) {
        var movie = findById(id);
        movie.setTitle(updateMovie.getName());
        movie.setDescription(updateMovie.getDescription());
        movie.setLength(updateMovie.getDuration());
        movie.setReleaseYear(updateMovie.getReleaseYear());
        movie.setActors((List<Actor>) getAllActorsForIds(updateMovie.getActors()));
        movie.setStudio(findStudioById(id));
        movie.setGenre(updateMovie.getGenre());
        return movieRepository.save(movie);
    }

    public Iterable<Movie> findMoviesByTitleContains(String searchString) {
//        return movieRepository.findMoviesByTitleContains(searchString);
        return movieRepository.findAll();
    }

    private Iterable<Actor> getAllActorsForIds(List<Long> actorIds) {
        return actorRepository.findAllById(actorIds);
    }

    private Studio findStudioById(long id) {
        return studioRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Studio not found"));
    }



}