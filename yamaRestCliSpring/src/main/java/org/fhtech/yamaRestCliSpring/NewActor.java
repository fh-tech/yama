package org.fhtech.yamaRestCliSpring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

// naming of variables is like in json so no explicit binding necessary

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewActor {
    private Sex sex;
    private String firstName;
    private String lastName;
    // String representation to send "YYYY-MM-DD"
    private LocalDate birthDay;
    private List<Long> movieIds;
}
