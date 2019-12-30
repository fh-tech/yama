package org.fhtech.yamaRestCliSpring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Actor {
    private Long id;

    private Sex sex;

    private String firstName;

    private String lastName;

    private LocalDate birthDay;

//    private List<Movie> movies;
}
