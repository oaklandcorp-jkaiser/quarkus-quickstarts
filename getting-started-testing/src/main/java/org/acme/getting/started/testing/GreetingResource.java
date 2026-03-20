package org.acme.getting.started.testing;

import java.io.ByteArrayInputStream;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws JAXBException {
        var record = "<greeting message=\"hello\"></greeting>";
        var unmarshaller = JAXBContext.newInstance(GreetingRecord.class).createUnmarshaller();
        var greeting = (GreetingRecord) unmarshaller.unmarshal(new ByteArrayInputStream(record.getBytes()));
        return greeting.getMessage();
    }

    @XmlRootElement(name = "greeting")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GreetingRecord {

        @XmlAttribute(name = "message", required = true)
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
