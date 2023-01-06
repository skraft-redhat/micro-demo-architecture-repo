package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/")
public class GreetingResource {
    @ConfigProperty(name="quarkus.container-image.tag")
    String version;

    private String HOSTNAME = System.getenv().getOrDefault("HOSTNAME", "unknown");



    @GET
    public String hello() {
        return String.format("Hello my friend in version %s from host %s\n", version, HOSTNAME);
    }
}