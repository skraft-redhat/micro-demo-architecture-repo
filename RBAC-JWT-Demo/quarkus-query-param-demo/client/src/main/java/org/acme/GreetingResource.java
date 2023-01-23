package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")

public class GreetingResource {

    @Inject
    @RestClient
    ExternalService externalService; 

    @GET
    @Path("/AdminService")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloAdmin() {
        return externalService.AdminService();   
    }

    @Path("/UserService")
    @Produces(MediaType.TEXT_PLAIN)

    public String helloUser() {
        return externalService.UserService();   
    }
}