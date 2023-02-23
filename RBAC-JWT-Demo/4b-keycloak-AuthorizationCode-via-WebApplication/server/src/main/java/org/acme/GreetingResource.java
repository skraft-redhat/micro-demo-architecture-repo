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


@Path("/server")
public class GreetingResource {
    private static final Logger LOG = Logger.getLogger(GreetingResource.class);


    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/userService")
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public String userService() {
        LOG.info("Received request from client");

        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s\n\nJWT Token is issued for: %s",hasJwt(),getJwt(),jwt.getName());    
    }

    @GET
    @Path("/adminService")
    @RolesAllowed("admin")    
    @Produces(MediaType.TEXT_PLAIN)
    public String secured_from_server() {
        LOG.info("Received request from client");
        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s\n\nJWT Token is issued for: %s",hasJwt(),getJwt(),jwt.getName());    
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }

    private String getJwt() {
        return jwt.getRawToken();
    }

    private String getName() {
        return jwt.getName();
    }
}