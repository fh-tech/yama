package org.fhtech.yama;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Actor {

    private Long id;
    private Gender gender;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private List<Movie> movies;
}
