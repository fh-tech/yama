package org.fhtech.yamaRestCliSpring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Studio {
    private Long id;

    private String name;

    private String postCode;

    private String countryCode;

//    private List<Movie> movies;
}
