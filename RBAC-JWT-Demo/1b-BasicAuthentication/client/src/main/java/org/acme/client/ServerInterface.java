package org.acme.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/server")
@RegisterRestClient
@RegisterClientHeaders()
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
