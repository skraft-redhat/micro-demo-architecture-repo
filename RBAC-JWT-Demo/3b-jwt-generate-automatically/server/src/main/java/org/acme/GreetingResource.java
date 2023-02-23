package org.acme;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;


import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Path("/server")
public class GreetingResource {
    private static final Logger LOG = Logger.getLogger(GreetingResource.class);


    @Inject
    JsonWebToken jwt;

    @GET
    @Path("publicService")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String public_from_server() {
        LOG.info("Received request from client");

        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s",hasJwt(),getJwt());
    }

    @GET
    @Path("userService")
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public String userService_from_server() {
        LOG.info("Received request from client");

        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s",hasJwt(),getJwt());
    }

    @GET
    @Path("adminService")
    @RolesAllowed("admin")

    @Produces(MediaType.TEXT_PLAIN)
    public String AdminService_from_server() {
        LOG.info("Received request from client");

        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s",hasJwt(),getJwt());    }
    
        private String getJwt() {
        return jwt.getRawToken();
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}