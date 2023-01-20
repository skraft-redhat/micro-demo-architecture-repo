package org.acme;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import io.quarkus.security.Authenticated;

@Path("/")
public class GreetingResource {
    private static final Logger LOG = Logger.getLogger(GreetingResource.class);


    @Inject
    JsonWebToken jwt;

    @GET
    @Path("public_from_server")

    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String public_from_server() {
        LOG.info("Received request from client");

        return String.format("Hello from Server. Has JWT: %s",hasJwt());
    }

    @GET
    @Path("secured_from_server")
//    @Authenticated
 
    @RolesAllowed("admin")
    
    @Produces(MediaType.TEXT_PLAIN)
    public String secured_from_server() {
        LOG.info("Received request from client");

        return String.format("Hello from Server. Has JWT: %s\n\nThe JWT is: %s",hasJwt(),jwt.getRawToken());
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}