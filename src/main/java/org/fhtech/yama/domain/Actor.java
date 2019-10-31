package org.fhtech.yama.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Value
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String firstName;

    private String lastName;

    private LocalDate birthDay;

    @ManyToMany()
    @JoinTable(
        name = "actor_movies",
        joinColumns = {@JoinColumn(name = "actor_id")},
        inverseJoinColumns = {@JoinColumn(name= "movie_id")}
    )
    private List<Movie> movies;
}
