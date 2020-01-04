package org.fhtech.consumingwebservice;


import org.apache.http.auth.UsernamePasswordCredentials;
import org.fhtech.consumingwebservice.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class MovieClient {


    private static final Logger log = LoggerFactory.getLogger(MovieClient.class);
    private final WebServiceTemplate movieTemplate;
    private final Unmarshaller unmarshaller;

    public MovieClient(@Autowired WebServiceTemplate movieTemplate, @Autowired Unmarshaller unmarshaller) {
        this.movieTemplate = movieTemplate;
        this.unmarshaller = unmarshaller;
    }

    public SearchMovieResponse searchMovie(String searchString) {

        var request = new SearchMovieRequest();
        request.setSearchString(searchString);
        log.info("Searching for movies containing: " + searchString);

        return (SearchMovieResponse) movieTemplate
                .marshalSendAndReceive("http://localhost:8080/ws/movies", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/movies/searchMovieRequest"));
    }

    public ImportMovieResponse importMovies(String filePath) throws Exception {

        var source = new StreamSource(new File(filePath));
        var jaxElement = unmarshaller.unmarshal(source, Movies.class);

        var request = new ImportMovieRequest();
        request.getMovies().addAll(jaxElement.getValue().getMovie());

        return (ImportMovieResponse) movieTemplate
                .marshalSendAndReceive("http://localhost:8080/ws/movies", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/movies/importMovieRequest"));
    }
}
