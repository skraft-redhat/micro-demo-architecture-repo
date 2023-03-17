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
import org.jboss.resteasy.reactive.NoCache;

import io.quarkus.security.Authenticated;

@Path("/servicea")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    @RestClient
    @Inject
    ServerInterface serverInterface;

    @GET
    @Path("publicService")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String calling_publicService() {
        LOG.info("Received request - forwarding to server");
        return serverInterface.publicService();
    }

    @GET
    @Path("userService")
    @Produces(MediaType.TEXT_PLAIN)
    public String calling_UserService() {
        LOG.info("Received request - forwarding to server");

        return serverInterface.userService();
    }

    @GET
    @Path("adminService")
    @Produces(MediaType.TEXT_PLAIN)
    public String calling_AdminService() {
        LOG.info("Received request - forwarding to server");
        return serverInterface.adminService();
    }
}