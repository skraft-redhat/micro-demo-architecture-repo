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
    public String serveLocally() {
        
        LOG.info("Received request - serving locally");
        return String.format("Hello from client. Has JWT: %s.\n\nThe JWT is: %s",hasJwt(),jwt.getRawToken());
    }

    @GET
    @Path("/local/user")
    @Produces(MediaType.TEXT_PLAIN)
    public String call_userService() {
        LOG.info("Received request - serving locally");
        return String.format("Hello from client. Has JWT: %s.\n\nThe JWT is: %s",hasJwt(),jwt.getRawToken());
    }

    @GET
    @Path("/userService")
    @Produces(MediaType.TEXT_PLAIN)
    public String call_adminService() {
        LOG.info("Received request - forwarding to server");
        return serverInterface.userService();
    }

    @GET
    @Path("adminService")
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public String secured_from_server() {
        LOG.info("Received request - forwarding to server");

        return serverInterface.adminService();
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}