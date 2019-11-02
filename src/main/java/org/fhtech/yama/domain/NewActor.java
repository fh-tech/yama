package org.fhtech.yama.domain;
import lombok.Value;
import java.time.LocalDate;
import java.util.List;

@Value
public class NewActor {
    private Gender gender;
    private String firstName;
    private String lastName;
    // String representation to send "YYYY-MM-DD"
    private LocalDate birthDay;
    private List<Long> movieIds;
}
