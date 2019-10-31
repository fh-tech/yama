package org.fhtech.yama.controllers;

import org.fhtech.yama.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MovieEndpoint {

    private final static String NAME_SPACE_URI = "http://fhtech.org/yama/movies";

    private final MovieRepository movies;

    public MovieEndpoint(MovieRepository movies) {
        this.movies = movies;
    }

    @PayloadRoot(namespace = NAME_SPACE_URI, localPart = "getMovies")
    @ResponsePayload
    public void getMovies(){
        return;
    }
}
