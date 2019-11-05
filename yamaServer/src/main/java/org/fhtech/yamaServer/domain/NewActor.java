package org.fhtech.yamaServer.domain;
import lombok.Value;
import java.time.LocalDate;
import java.util.List;

@Value
public class NewActor {
    private Sex sex;
    private String firstName;
    private String lastName;
    // String representation to send "YYYY-MM-DD"
    private LocalDate birthDay;
    private List<Long> movieIds;
}
