package org.fhtech.yama.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    // always in minutes
    private short length;

    private short releaseYear;

    @ManyToMany()
    @JoinTable(
            name = "actor_movies",
            joinColumns = {@JoinColumn(name= "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    // to avoid circular reference
    @JsonIgnoreProperties("movies")
    private List<Actor> actors;

    @ManyToOne()
    // to avoid circular reference
    @JsonIgnoreProperties("movies")
    private Studio studio;

    @Enumerated(EnumType.STRING)
    private Genre genre;

}
