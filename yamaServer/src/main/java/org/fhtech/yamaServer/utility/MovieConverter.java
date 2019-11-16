package org.fhtech.yamaServer.utility;


import org.fhtech.yama.movies.*;
import org.fhtech.yamaServer.domain.Movie;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieConverter {


    public static Movie convertToDomainMovie(org.fhtech.yama.movies.Movie movie) {
        var domainMovie = new Movie();

        var domainActors = new ArrayList<org.fhtech.yamaServer.domain.Actor>();
        movie.getActors().getActor().forEach(xmlActor -> {
            var domainActor = new org.fhtech.yamaServer.domain.Actor();
            domainActor.setLastName(xmlActor.getLastname());
            domainActor.setFirstName(xmlActor.getFirstname());
            var xmlGregorianCalendar = xmlActor.getBirthdate();
            LocalDate localDate = LocalDate.of(
                    xmlGregorianCalendar.getYear(),
                    xmlGregorianCalendar.getMonth(),
                    xmlGregorianCalendar.getDay());
            domainActor.setBirthDay(localDate);
            domainActor.setSex("MALE".equals(xmlActor.getSex().toString()) ? org.fhtech.yamaServer.domain.Sex.MALE : org.fhtech.yamaServer.domain.Sex.FEMALE);
            domainActors.add(domainActor);
        });
        domainMovie.setActors(domainActors);

        domainMovie.setDescription(movie.getDescription());
        domainMovie.setGenre(convertToDomainGenre(movie.getGenre()));
        domainMovie.setLength(movie.getLength());
        domainMovie.setReleaseYear(movie.getReleaseyear());
        domainMovie.setTitle(movie.getTitle());

        var xmlStudio = movie.getStudio();
        var domainStudio = new org.fhtech.yamaServer.domain.Studio();
        domainStudio.setCountryCode(xmlStudio.getCountrycode());
        domainStudio.setName(xmlStudio.getName());
        domainStudio.setPostCode(xmlStudio.getPostcode());
        domainStudio.setMovies(new ArrayList<>());
        return domainMovie;
    }


    public static List<org.fhtech.yama.movies.Movie> convertToXMLMovies(Iterable<Movie> movies) {
        var xmlMovies = new ArrayList<org.fhtech.yama.movies.Movie>();
        movies.forEach(domainMovie -> xmlMovies.add(convertToXMLMovie(domainMovie)));
        return xmlMovies;
    }

    public static org.fhtech.yama.movies.Movie convertToXMLMovie(Movie movie) {
        var xmlMovie = new org.fhtech.yama.movies.Movie();
        var xmlActors = new ArrayList<Actor>();

        movie.getActors().forEach(domainActor -> {
            var xmlActor = new Actor();
            xmlActor.setSex("MALE".equals(domainActor.getSex().toString()) ? Sex.MALE : Sex.FEMALE);
            try {
                var xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(domainActor.getBirthDay().toString());
                xmlActor.setBirthdate(xmlGregorianCalendar);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
            xmlActor.setFirstname(domainActor.getFirstName());
            xmlActor.setLastname(domainActor.getLastName());
            xmlActors.add(xmlActor);
        });
        var actors = new Actors();
        actors.getActor().addAll(xmlActors);
        xmlMovie.setActors(actors);
        xmlMovie.setDescription(movie.getDescription());
        xmlMovie.setLength(movie.getLength());
        xmlMovie.setReleaseyear(movie.getReleaseYear());
        xmlMovie.setGenre(convertToXMLGenre(movie.getGenre()));

        //studio
        var domainStudio = movie.getStudio();
        var xmlStudio = new Studio();

        xmlStudio.setCountrycode(domainStudio.getCountryCode());
        xmlStudio.setName(domainStudio.getName());
        xmlStudio.setPostcode(domainStudio.getPostCode());

        return xmlMovie;
    }


    private static Genre convertToXMLGenre(org.fhtech.yamaServer.domain.Genre genre) {
        switch (genre) {
            case DRAMA:
                return Genre.DRAMA;
            case ACTION:
                return Genre.ACTION;
            case COMEDY:
                return Genre.COMEDY;
            case HORROR:
                return Genre.HORROR;
            case WESTERN:
                return Genre.WESTERN;
            case THRILLER:
                return Genre.THRILLER;
            case ANIMATION:
                return Genre.ANIMATION;
            case SCIENCE_FICTION:
                return Genre.SCIENCE_FICTION;
            default:
                throw new RuntimeException("unknown genre");
        }
    }

    private static org.fhtech.yamaServer.domain.Genre convertToDomainGenre(Genre genre) {
        switch (genre) {
            case DRAMA:
                return org.fhtech.yamaServer.domain.Genre.DRAMA;
            case ACTION:
                return org.fhtech.yamaServer.domain.Genre.ACTION;
            case COMEDY:
                return org.fhtech.yamaServer.domain.Genre.COMEDY;
            case HORROR:
                return org.fhtech.yamaServer.domain.Genre.HORROR;
            case WESTERN:
                return org.fhtech.yamaServer.domain.Genre.WESTERN;
            case THRILLER:
                return org.fhtech.yamaServer.domain.Genre.THRILLER;
            case ANIMATION:
                return org.fhtech.yamaServer.domain.Genre.ANIMATION;
            case SCIENCE_FICTION:
                return org.fhtech.yamaServer.domain.Genre.SCIENCE_FICTION;
            default:
                throw new RuntimeException("unknown genre");
        }
    }


}


