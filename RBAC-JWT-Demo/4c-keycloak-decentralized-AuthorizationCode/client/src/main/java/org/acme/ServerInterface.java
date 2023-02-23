package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@RegisterClientHeaders(RequestJWTHeaderFactory.class)
@Path("/server")
public interface ServerInterface {
    
    @GET
    @Path("/userService")
    String userService();

    @GET
    @Path("/adminService")
    String adminService();
}
