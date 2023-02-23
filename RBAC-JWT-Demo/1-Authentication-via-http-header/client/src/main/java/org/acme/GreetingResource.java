package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/client")

public class GreetingResource {

    @Inject
    @RestClient
    ExternalService externalService; 

    @GET
    @Path("/publicService")
    @Produces(MediaType.TEXT_PLAIN)
    public String call_publicService() {

        return externalService.publicService();   
    }

    @GET
    @Path("/adminService")
    @Produces(MediaType.TEXT_PLAIN)
    public String call_adminService(@QueryParam("role") String role) {
        if (role == null) {
            throw new WebApplicationException(
              Response.status(Response.Status.BAD_REQUEST)
                .entity("Role parameter is mandatory")
                .build()
            );
        }
        return externalService.adminService(role);   
    }

    @GET
    @Path("/userService")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloUser(@QueryParam("role") String role) {
        if (role == null) {
            throw new WebApplicationException(
              Response.status(Response.Status.BAD_REQUEST)
                .entity("Role parameter is mandatory")
                .build()
            );
        }
        return externalService.userService(role);   
    }
}