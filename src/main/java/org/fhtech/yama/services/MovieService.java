package org.fhtech.yama.services;

import org.fhtech.yama.domain.*;
import org.fhtech.yama.repositories.ActorRepository;
import org.fhtech.yama.repositories.MovieRepository;
import org.fhtech.yama.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        movie.setName(updateMovie.getName());
        movie.setDescription(updateMovie.getDescription());
        movie.setDuration(updateMovie.getDuration());
        movie.setReleaseYear(updateMovie.getReleaseYear());
        movie.setActors((List<Actor>) getAllActorsForIds(updateMovie.getActors()));
        movie.setStudio(findStudioById(id));
        movie.setGenre(updateMovie.getGenre());
        return movieRepository.save(movie);
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
