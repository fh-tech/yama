package org.fhtech.yama.domain;
import lombok.Value;

import java.util.List;

@Value
public class NewMovie {
    private String name;
    private String description;
    private int duration;
    private int releaseYear;
    private List<Long> actors;
    private long studioId;
    private Genre genre;
}
