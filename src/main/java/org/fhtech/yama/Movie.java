package org.fhtech.yama;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Movie {

    private Long id;
    private String name;
    private String description;
    // always in minutes
    private int duration;
    private LocalDate releaseYear;
    private List<Actor> actors;
    private Studio studio;
    private Genre genre;

}
