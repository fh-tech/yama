package org.fhtech.yama.utility;

import org.fhtech.yama.movies.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.ArrayList;
import java.util.List;

public class MovieConverter {


    public static List<Movie> convertToXMLMovies(Iterable<org.fhtech.yama.domain.Movie> movies) {
        var xmlMovies = new ArrayList<Movie>();
        movies.forEach(domainMovie -> xmlMovies.add(convertToXMLMovie(domainMovie)));
        return xmlMovies;
    }


    public static Movie convertToXMLMovie(org.fhtech.yama.domain.Movie movie) {
        var xmlMovie = new Movie();
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


    private static Genre convertToXMLGenre(org.fhtech.yama.domain.Genre genre) {
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


