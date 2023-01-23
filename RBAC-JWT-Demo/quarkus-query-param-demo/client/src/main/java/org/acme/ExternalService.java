package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientQueryParam;

@RegisterRestClient
@Path("/GreetingService")
public interface ExternalService {

    @GET
    @ClientQueryParam(name="role", value ="admin")
    String AdminService ();

    @GET
    @ClientQueryParam(name="role", value ="user")
    String UserService ();
}
