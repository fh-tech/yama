package org.fhtech.consumingwebservice;

import org.fhtech.consumingwebservice.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.xml.transform.StringSource;
import javax.xml.bind.JAXBElement;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MovieClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(MovieClient.class);

    public SearchMovieResponse searchMovie(String searchString) {

        var request = new SearchMovieRequest();
        request.setSearchString(searchString);
        log.info("Searching for movies containing: " + searchString);

        return (SearchMovieResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/movies", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/movies/searchMovieRequest"));
    }

    public ImportMovieResponse importMovies(String filePath) throws IOException {
        var request = new ImportMovieRequest();
        var xmlString = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        var movies = (JAXBElement<Movies>) this.getUnmarshaller().unmarshal(new StringSource(xmlString));
        request.getMovies().addAll(movies.getValue().getMovie());
        return (ImportMovieResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/movies", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/movies/importMovieRequest"));
    }



}
