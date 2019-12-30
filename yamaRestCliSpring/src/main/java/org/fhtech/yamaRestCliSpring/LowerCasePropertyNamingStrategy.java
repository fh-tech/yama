package org.fhtech.yamaRestCliSpring;

import javax.json.bind.config.PropertyNamingStrategy;

public class LowerCasePropertyNamingStrategy implements PropertyNamingStrategy {
    @Override
    public String translateName(String propertyName) {
        return propertyName.toLowerCase();
    }
}
