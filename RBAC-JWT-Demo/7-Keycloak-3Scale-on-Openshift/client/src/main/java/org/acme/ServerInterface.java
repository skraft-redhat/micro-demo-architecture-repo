package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/serviceb")
@RegisterRestClient
@RegisterClientHeaders(RequestJWTHeaderFactory.class)
public interface ServerInterface {
    
    @GET
    @Path("/userEP")
    String userService();

    @GET
    @Path("/adminEP")
    String adminService();

    @GET
    @Path("/publicEP")
    String publicService();
}
