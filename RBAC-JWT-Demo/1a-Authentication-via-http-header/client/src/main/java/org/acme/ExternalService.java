package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientQueryParam;

@RegisterRestClient
@Path("/GreetingService")
public interface ExternalService {

    @GET
    @Path("admin")
    String AdminService (@QueryParam("role") String role);

    @GET
    @Path("user")
    String UserService (@QueryParam("role") String role);
}
