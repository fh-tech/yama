package org.fhtech.yamaRestCliSpring.consumingRest;

import org.apache.commons.cli.*;
import org.fhtech.yamaRestCliSpring.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@SpringBootApplication
public class ConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);
    private static final String STUDIO_ENDPOINT = "http://127.0.0.1:8080/api/studio";
    private static final String ACTOR_ENDPOINT = "http://127.0.0.1:8080/api/actor";

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
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
                    if(actor) {
                        createActor(inputFilePath);
                    } else {
                        createStudio(inputFilePath);
                    }
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                new HelpFormatter().printHelp("utility-name", options);
                System.exit(1);
            }
            // just to let it finish for now
            System.exit(0);
        };
    }

    private static void createActor(String filePath) throws Exception {
        var newActor = Deserializer.convertJSONToNewActor(filePath);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("writer", "123"));
        var result = restTemplate.postForObject(ACTOR_ENDPOINT, newActor, Actor.class);
        System.out.println(result);
    }

    private static void createStudio(String filePath) throws Exception {
        var newStudio = Deserializer.convertJSONToNewStudio(filePath);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("writer", "123"));
        var result = restTemplate.postForObject(STUDIO_ENDPOINT, newStudio, Studio.class);
        System.out.println(result);
    }
}
