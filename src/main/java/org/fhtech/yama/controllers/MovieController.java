package org.fhtech.yama.controllers;

import org.fhtech.yama.domain.Movie;
import org.fhtech.yama.domain.NewMovie;
import org.fhtech.yama.domain.UpdateMovie;
import org.fhtech.yama.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie createMovie(@Valid @RequestBody NewMovie newMovie) {
        return this.movieService.save(newMovie);
    }

    @GetMapping
    public Iterable<Movie> getMovies() {
        return this.movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable long id) {
        return this.movieService.findById(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable long id, @Valid @RequestBody UpdateMovie updateMovie) {
        return this.movieService.update(id, updateMovie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable long id) {
        this.movieService.deleteById(id);
    }


}
