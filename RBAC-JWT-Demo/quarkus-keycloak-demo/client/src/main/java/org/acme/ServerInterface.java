package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(RequestJWTHeaderFactory.class)
public interface ServerInterface {
    
    @GET
    @Path("/public_from_server")
    String public_from_server();

    @GET
    @Path("/secured_from_server")
    String secured_from_server();
}
