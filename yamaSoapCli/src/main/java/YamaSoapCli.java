import org.apache.commons.cli.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class YamaSoapCli {

    private static final String MOVIE_ENDPOINT = "http://localhost:8080/ws";

    // TODO: was soll der input von RESTCli sein? ein json file?
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        try {
            Option input = new Option("f", "file", true, "path to the input file");
            input.setArgName("filepath");
            input.setRequired(true);
            options.addOption(input);

            var cmd = new DefaultParser().parse(options, args);
            String inputFilePath = cmd.getOptionValue("f");
            createResource(inputFilePath);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("utility-name", options);
            System.exit(1);
        }
    }

    private static void createResource(String filePath) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MOVIE_ENDPOINT))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "text/xml")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(filePath)))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }





}
