package org.fhtech.consumingwebservice;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.fhtech.consumingwebservice.wsdl.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

@Configuration
public class MovieConfiguration {

    private String defaultUri = "http://localhost:8080/ws";
    private String userName = "writer";
    private String userPassword = "123";


    @Bean
    public Unmarshaller unMarshaller() throws Exception {
        var jaxbContext = JAXBContext.newInstance(Movies.class);
        return jaxbContext.createUnmarshaller();
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        var marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.fhtech.consumingwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate movieTemplate(@Autowired Jaxb2Marshaller marshaller, @Autowired HttpComponentsMessageSender sender){
        var template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        template.setDefaultUri(defaultUri);
        template.setMessageSender(sender);
        return template;
    }

    @Bean
    HttpComponentsMessageSender httpComponentsMessageSender(){
        var sender = new HttpComponentsMessageSender();
        sender.setCredentials(new UsernamePasswordCredentials(userName, userPassword));
        return sender;
    }

}
