package org.fhtech.yama;

import lombok.Data;

import java.util.List;

@Data
public class Studio {

    private String name;
    private List<Movie> movies;

}
