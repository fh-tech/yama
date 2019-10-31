package org.fhtech.yama.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Value
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    // always in minutes
    private int duration;

    private LocalDate releaseYear;

    @ManyToMany()
    @JoinTable(
            name = "actor_movies",
            joinColumns = {@JoinColumn(name= "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private List<Actor> actors;

    @ManyToOne()
    private Studio studio;

    @Enumerated(EnumType.STRING)
    private Genre genre;

}
