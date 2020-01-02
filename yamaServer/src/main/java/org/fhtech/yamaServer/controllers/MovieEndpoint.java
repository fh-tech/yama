package org.fhtech.yamaServer.controllers;


import org.fhtech.yama.movies.*;
import org.fhtech.yamaServer.services.MovieService;
import org.fhtech.yamaServer.utility.MovieConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

@Endpoint
public class MovieEndpoint {
    private static final String NAMESPACE_URI = "http://fhtech.org/yama/movies";

    private MovieService movieService;

    public MovieEndpoint(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchMovieRequest")
    @ResponsePayload
    public SearchMovieResponse getMovies(@RequestPayload SearchMovieRequest request) {
        SearchMovieResponse response = new SearchMovieResponse();
        var domainMovies = movieService.findMoviesByTitleContains(request.getSearchString());
        response.getMovies()
                .addAll(MovieConverter.convertToXMLMovies(domainMovies));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "importMovieRequest")
    @ResponsePayload
    public ImportMovieResponse importMovies(@RequestPayload ImportMovieRequest request) {
        ImportMovieResponse response = new ImportMovieResponse();
        var domainMovies = request.getMovies().stream().map(MovieConverter::convertToDomainMovie).collect(Collectors.toList());
        movieService.importMovies(domainMovies);
        return response;
    }


}