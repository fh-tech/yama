package org.fhtech.yama.controllers;

import org.fhtech.yama.domain.Movie;
import org.fhtech.yama.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private MovieRepository movies;

    public MainController(@Autowired MovieRepository movies) {
        this.movies = movies;
    }

    @RequestMapping
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping("movies")
    public Iterable<Movie> movies(){
        return movies.findAll();
    }


}
