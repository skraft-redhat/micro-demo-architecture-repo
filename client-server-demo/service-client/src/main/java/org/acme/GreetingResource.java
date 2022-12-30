package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/greeting")
public class GreetingResource {
    
    @Inject
    @RestClient
    RestServiceProxy proxy;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("language") String language) {
        String greeting=""; 
        if (language.equals("englisch")) {
            greeting=proxy.getEnglisch();
        }
        
        if (language.equals("deutsch")) {
            greeting=proxy.getDeutsch();
        }

        if (language.equals("franzoesisch")) {
            greeting=proxy.getFranzoesisch();
        }

        return greeting;
    }
}