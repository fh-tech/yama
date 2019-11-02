package org.fhtech.yama.domain;

import lombok.Value;

@Value
public class UpdateStudio {
    private String name;
    private String postCode;
    private String countryCode;
}
