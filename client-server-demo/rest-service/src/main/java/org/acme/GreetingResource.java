package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {

    @GET
    @Path("/englisch")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello ";
    }

    @GET
    @Path("deutsch")
    @Produces(MediaType.TEXT_PLAIN)
    public String hallo() {
        return "Hallo ";
    }

    @GET
    @Path("franzoesisch")
    @Produces(MediaType.TEXT_PLAIN)
    public String bonjour() {
        return "Bonjour ";
    }
}