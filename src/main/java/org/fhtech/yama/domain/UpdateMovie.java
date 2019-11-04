package org.fhtech.yama.domain;

import lombok.Value;

import java.util.List;

@Value
public class UpdateMovie {
    private String name;
    private String description;
    private short duration;
    private short releaseYear;
    private List<Long> actors;
    private long studioId;
    private Genre genre;
}
