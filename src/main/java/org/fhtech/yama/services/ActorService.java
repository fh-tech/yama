package org.fhtech.yama.services;

import org.fhtech.yama.domain.Actor;
import org.fhtech.yama.domain.Movie;
import org.fhtech.yama.domain.NewActor;
import org.fhtech.yama.domain.UpdateActor;
import org.fhtech.yama.repositories.ActorRepository;
import org.fhtech.yama.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Component
public class ActorService {

    private ActorRepository actorRepository;
    private MovieRepository movieRepository;

    public ActorService(@Autowired ActorRepository actorRepository,
                        @Autowired MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    public Iterable<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor save(NewActor newActor) {
        Actor actor = new Actor(
                null,
                newActor.getSex(),
                newActor.getFirstName(),
                newActor.getLastName(),
                newActor.getBirthDay(),
                (List<Movie>) getAllMoviesForIds(newActor.getMovieIds())
        );
        return actorRepository.save(actor);
    }

    public Actor findById(long id) {
        return actorRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));
    }

    public void deleteById(long id) {
        if (!actorRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");
        actorRepository.deleteById(id);
    }

    public Actor update(long id, UpdateActor updateActor) {
        var actor = findById(id);
        actor.setBirthDay(updateActor.getBirthDay());
        actor.setFirstName(updateActor.getFirstName());
        actor.setLastName(updateActor.getLastName());
        actor.setSex(updateActor.getSex());
        actor.setMovies((List<Movie>) getAllMoviesForIds(updateActor.getMovieIds()));
        return actorRepository.save(actor);
    }

    private Iterable<Movie> getAllMoviesForIds(List<Long> movieIds) {
        return movieRepository.findAllById(movieIds);
    }

}
