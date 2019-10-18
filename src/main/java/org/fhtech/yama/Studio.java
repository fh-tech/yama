package org.fhtech.yama;

import lombok.Data;

import java.util.List;

@Data
public class Studio {

    private Long id;
    private String name;
    private String postCode;
    private String countryCode;
    private List<Movie> movies;

}
