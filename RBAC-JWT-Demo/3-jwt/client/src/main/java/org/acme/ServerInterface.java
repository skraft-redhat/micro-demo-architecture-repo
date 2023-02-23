package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/server")
@RegisterRestClient
@RegisterClientHeaders(RequestJWTHeaderFactory.class)
public interface ServerInterface {
    
    @GET
    @Path("/userService")
    String userService();

    @GET
    @Path("/adminService")
    String adminService();

    @GET
    @Path("/publicService")
    String publicService();
}
