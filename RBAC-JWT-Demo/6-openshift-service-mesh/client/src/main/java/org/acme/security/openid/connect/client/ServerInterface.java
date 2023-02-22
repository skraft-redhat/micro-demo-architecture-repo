package org.acme.security.openid.connect.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;

@RegisterRestClient
@Path("/protected")
public interface ServerInterface {

    @GET
    @Produces("text/plain")
    @Path("userName")
    Uni<String> getUserName();
    
    @GET
    @Produces("text/plain")
    @Path("adminName")
    Uni<String> getAdminName();
}
