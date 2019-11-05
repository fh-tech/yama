package org.fhtech.yamaServer.domain;

import lombok.Value;

@Value
public class NewStudio {
    private String name;
    private String postCode;
    private String countryCode;
}
