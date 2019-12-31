package org.fhtech.yamaSoapCli;

import org.apache.commons.cli.*;
import org.fhtech.yama.movies.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class YamaSoapCli {

    public static void main(String[] args) throws Exception {

        Options options = new Options();
        try {
            Option input = new Option("f", "file", true, "path to the input file");
            input.setArgName("filepath");
            input.setRequired(true);
            options.addOption(input);

            var cmd = new DefaultParser().parse(options, args);
            String inputFilePath = cmd.getOptionValue("f");
            loadMovies(inputFilePath);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("utility-name", options);
            System.exit(1);
        }
    }

    private static ImportMovieResponse importMovies(ImportMovieRequest importMovieRequest) {
        var moviesService = new MoviesPortService();
        var client = moviesService.getMoviesPortSoap11();
        return client.importMovie(importMovieRequest);
    }

    private static List<Movie> loadMovies(String filePath) throws JAXBException {

        var jaxbContext = JAXBContext.newInstance(Movies.class);
        var unmarshaller = jaxbContext.createUnmarshaller();
        var source = new StreamSource(new File(filePath));
        var jaxElement = unmarshaller.unmarshal(source, Movies.class);
        var movies = jaxElement.getValue().getMovie();
        return movies;
    }

}
