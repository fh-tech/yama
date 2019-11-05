package org.fhtech.yamaServer.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String firstName;

    private String lastName;

    private LocalDate birthDay;

    @ManyToMany()
    @JoinTable(
        name = "actor_movies",
        joinColumns = {@JoinColumn(name = "actor_id")},
        inverseJoinColumns = {@JoinColumn(name= "movie_id")}
    )
    @JsonIgnoreProperties("actors")
    private List<Movie> movies;
}
