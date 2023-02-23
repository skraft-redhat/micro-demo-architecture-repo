package org.acme;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Path("/client")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);


    @Inject
    JsonWebToken jwt;

    @RestClient
    @Inject
    ServerInterface serverInterface;

    @GET
    @Path("/local/public")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String public_locally() {    
        LOG.info("Received request - serving locally");
        return String.format("Hello from client. Has JWT: %s\n\nJWT Token is: %s\n\nJWT Token is issued for: %s",hasJwt(),jwt.getRawToken(),jwt.getName());    
    }

    @GET
    @Path("/local/user")
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public String user_locally() {    
        LOG.info("Received request - serving locally");
        return String.format("Hello from client. Has JWT: %s\n\nJWT Token is: %s\n\nJWT Token is issued for: %s",hasJwt(),jwt.getRawToken(),jwt.getName());    
    }


    @GET
    @Path("/userService")
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public String call_userService() {
        LOG.info("Received request - forwarding to server");
        return serverInterface.userService();
    }

    @GET
    @Path("adminService")
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String call_adminService() {
        LOG.info("Received request - forwarding to server");

        return serverInterface.adminService();
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}