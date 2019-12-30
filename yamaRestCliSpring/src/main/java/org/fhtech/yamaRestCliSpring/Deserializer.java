package org.fhtech.yamaRestCliSpring;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.FileReader;

public class Deserializer {

    public static NewActor convertJSONToNewActor(String filePath) throws Exception {
        try (
                var jsonb = JsonbBuilder.create(config())
        ) {
            return jsonb.fromJson(new FileReader(filePath), NewActor.class);
        }
    }

    public static NewStudio convertJSONToNewStudio(String filePath) throws Exception {
        try (
                var jsonb = JsonbBuilder.create(config())
        ) {
            return jsonb.fromJson(new FileReader(filePath), NewStudio.class);
        }
    }

    private static JsonbConfig config() {
        return new JsonbConfig()
//                .withPropertyNamingStrategy(new LowerCasePropertyNamingStrategy())
                .withFormatting(true);
    }
}
