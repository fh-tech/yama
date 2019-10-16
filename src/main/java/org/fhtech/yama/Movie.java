package org.fhtech.yama;

import lombok.Data;

import java.util.List;

@Data
public class Movie {

    private String name;
    // always in minutes
    private int duration;
    private List<Actor> actors;
    private Studio studio;

}
