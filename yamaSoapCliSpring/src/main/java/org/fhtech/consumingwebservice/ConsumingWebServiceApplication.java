package org.fhtech.consumingwebservice;

import org.apache.commons.cli.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(MovieClient movieClient) {
        return args -> {
            Options options = new Options();
            try {
                Option input = new Option("f", "file", true, "path to the input file");
                input.setArgName("filepath");
                input.setRequired(true);
                options.addOption(input);

                var cmd = new DefaultParser().parse(options, args);
                String inputFilePath = cmd.getOptionValue("f");
                if(movieClient.importMovies(inputFilePath).isResult()) {
                    System.out.println("Import was successful");
                } else {
                    System.out.println("Import failed");
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                new HelpFormatter().printHelp("utility-name", options);
                System.exit(1);
            }
        };
    }
}
