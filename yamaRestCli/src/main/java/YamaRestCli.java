import org.apache.commons.cli.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class YamaRestCli {


    private static final String STUDIO_ENDPOINT = "http://127.0.0.1:8080/studio";
    private static final String ACTOR_ENDPOINT = "http://127.0.0.1:8080/actor";


    // TODO: was soll der input von RESTCli sein? ein json file?
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        try {
            Option endpointStudio = new Option("s", "studio", false, "operate on the studio resource");
            endpointStudio.setRequired(false);
            options.addOption(endpointStudio);

            Option endpointActor = new Option("a", "actor", false, "operate on the actor resource");
            endpointActor.setRequired(false);
            options.addOption(endpointActor);

            Option input = new Option("f", "file", true, "path to the input file");
            input.setRequired(true);
            input.setArgName("filepath");
            options.addOption(input);

            var cmd = new DefaultParser().parse(options, args);
            var studio = cmd.hasOption("s");
            var actor = cmd.hasOption("a");

            if(studio && actor) {
                System.out.println("You can not use both endpoints at once");
                System.exit(1);
            } else if(!studio && !actor) {
                System.out.println("You have to use at least one endpoint");
                System.exit(1);
            } else {
                String inputFilePath = cmd.getOptionValue("f");
                createResource(actor ? ACTOR_ENDPOINT : STUDIO_ENDPOINT, inputFilePath);
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("utility-name", options);
            System.exit(1);
        }
    }

    private static void createResource(String endpoint, String filePath) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(filePath)))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }


}
