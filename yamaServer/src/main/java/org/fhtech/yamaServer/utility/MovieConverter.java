package org.fhtech.yamaServer.utility;


import org.fhtech.yama.movies.Actor;
import org.fhtech.yama.movies.Genre;
import org.fhtech.yama.movies.Sex;
import org.fhtech.yama.movies.Studio;
import org.fhtech.yamaServer.domain.Movie;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.ArrayList;
import java.util.List;

public class MovieConverter {

//    public static Iterable<org.fhtech.yama.domain.Movie> convertToDomainMovies(Iterable<Movie> movies) {
//        var domainMovies = new ArrayList<org.fhtech.yama.domain.Movie>();
//        movies.forEach(xmlMovie -> domainMovies.add(convertToDomainMovie(xmlMovie)));
//        return domainMovies;
//    }
//
//    public static org.fhtech.yama.domain.Movie convertToDomainMovie(Movie movie) {
//
//        var domainActor = new org.fhtech.yama.domain.Actor();
//        movie.getActors().forEach(xmlActor -> {
//            domainActor.setLastName(xmlActor.getLastname());
//            domainActor.setFirstName(xmlActor.getFirstname());
//
//            var xmlGregorianCalendar = xmlActor.getBirthdate();
//            LocalDate localDate = LocalDate.of(
//                    xmlGregorianCalendar.getYear(),
//                    xmlGregorianCalendar.getMonth(),
//                    xmlGregorianCalendar.getDay());
//            domainActor.setBirthDay(localDate);
//            domainActor.setSex("MALE".equals(xmlActor.getSex().toString()) ? org.fhtech.yama.domain.Sex.MALE : org.fhtech.yama.domain.Sex.FEMALE);
//
//
//        });
//    }


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
        xmlMovie.getActors().addAll(xmlActors);
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


}


