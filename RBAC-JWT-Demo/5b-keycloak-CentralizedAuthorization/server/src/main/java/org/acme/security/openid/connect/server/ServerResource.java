package org.acme.security.openid.connect.server;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.NoCache;
import org.jboss.resteasy.reactive.RestHeader;


import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;


@Path("/server")
public class ServerResource {

    private static final Logger LOG = Logger.getLogger(ServerResource.class);

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("publicService")
//    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String public_from_server() {
        LOG.info("Received request from client");
        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s\n\nJWT Token is issued for: %s",hasJwt(),getJwt(),jwt.getName());
    }

    @GET
//    @RolesAllowed("user")
    @Produces("text/plain")   
    @Path("userService")
    public String userName() {
        LOG.info("Request received from client");
        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s\n\nJWT Token is issued for: %s",hasJwt(),getJwt(),jwt.getName());

    }
    
    @GET
 //   @RolesAllowed("admin")
    @Produces("text/plain")
    @Path("adminService")
    public String adminName() {
        LOG.info("Request received from client");

        return String.format("Hello from Server. Has JWT: %s\n\nJWT Token is: %s\n\nJWT Token is issued for: %s", hasJwt(),getJwt(),jwt.getName());
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
