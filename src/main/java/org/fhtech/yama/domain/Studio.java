package org.fhtech.yama.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Entity
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String postCode;

    private String countryCode;

    @OneToMany(mappedBy = "studio")
    private List<Movie> movies;
}
