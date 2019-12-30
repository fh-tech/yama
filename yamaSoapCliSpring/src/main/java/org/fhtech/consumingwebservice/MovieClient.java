package org.fhtech.consumingwebservice;

import org.fhtech.consumingwebservice.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import javax.xml.bind.JAXBContext;
import javax.xml.transform.stream.StreamSource;
import java.io.File;


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

    public ImportMovieResponse importMovies(String filePath) throws Exception {

        var jaxbContext = JAXBContext.newInstance(Movies.class);
        var unmarshaller = jaxbContext.createUnmarshaller();
        var source = new StreamSource(new File(filePath));
        var jaxElement = unmarshaller.unmarshal(source, Movies.class);

        var request = new ImportMovieRequest();
        var movies = jaxElement.getValue();
        request.getMovies().addAll(jaxElement.getValue().getMovie());
        return (ImportMovieResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/movies", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/movies/importMovieRequest"));
    }



}
