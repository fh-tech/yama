package org.fhtech.yamaRestCliSpring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// naming of variables is like in json so no explicit binding necessary

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewStudio {
    private String name;
    private String postCode;
    private String countryCode;
}
