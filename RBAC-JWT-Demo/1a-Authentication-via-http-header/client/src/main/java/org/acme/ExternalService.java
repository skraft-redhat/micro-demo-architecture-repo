package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient
@Path("/server")
public interface ExternalService {

    @GET
    @Path("publicService")
    String publicService ();

    @GET
    @Path("userService")
    String userService (@QueryParam("role") String role);

    @GET
    @Path("adminService")
    String adminService (@QueryParam("role") String role);
}
