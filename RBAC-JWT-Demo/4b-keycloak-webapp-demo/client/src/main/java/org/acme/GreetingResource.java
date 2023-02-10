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

@Path("/")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);


    @Inject
    JsonWebToken jwt;

    @RestClient
    @Inject
    ServerInterface serverInterface;

    @GET
    @Path("public_from_client")
    //@PermitAll
    //@Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    public String public_fromClient() {
        
        LOG.info("Received request - serving locally");

        return String.format("Hello from client. Has JWT: %s",hasJwt());
    }

    @GET
    @Path("secured_from_client")

//    @RolesAllowed({"user", "admin"})
    @Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    public String secured_fromClient() {
        LOG.info("Received request - serving locally");
        return String.format("Hello from client. Has JWT: %s. The JWT is: %s",hasJwt(),jwt.getRawToken());
    }

    @GET
    @Path("public_from_server")
    @Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    public String public_from_server() {
        LOG.info("Received request - forwarding to server");

        return serverInterface.public_from_server();
    }

    @GET
    @Path("secured_from_server")
    @NoCache
    //@RolesAllowed({"User", "Admin"})
    @Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    public String secured_from_server() {
        LOG.info("Received request - forwarding to server");

        return serverInterface.secured_from_server();
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}