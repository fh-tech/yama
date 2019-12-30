package org.fhtech.yamaServer.domain;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Value
public class NewActor {
    private Sex sex;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    // String representation to send "YYYY-MM-DD"
    @NotNull
    private LocalDate birthDay;
    private List<Long> movieIds;
}
