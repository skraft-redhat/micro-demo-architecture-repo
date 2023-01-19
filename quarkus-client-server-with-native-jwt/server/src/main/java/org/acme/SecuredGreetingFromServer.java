package org.acme;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.Authenticated;



@Path("/")
public class SecuredGreetingFromServer {

    @Inject
    JsonWebToken jwt;


    @GET
    @Path("/secured-greeting")
    @RolesAllowed("user")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRolesAllowed() {
       return "Hello " + jwt.getClaim("upn").toString();
    }

    
}