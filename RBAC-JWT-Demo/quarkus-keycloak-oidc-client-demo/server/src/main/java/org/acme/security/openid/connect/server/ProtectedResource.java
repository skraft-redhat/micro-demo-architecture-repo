package org.acme.security.openid.connect.server;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;


@Path("/protected")
@Authenticated
public class ProtectedResource {

    private static final Logger LOG = Logger.getLogger(ProtectedResource.class);

    @Inject
    JsonWebToken principal;

    @GET
 //   @Authenticated
    @RolesAllowed("user")
    @Produces("text/plain")
    @Path("userName")
    public Uni<String> userName() {
        LOG.info("Request received from client");
        return Uni.createFrom().item(principal.getName());
    }
    
    @GET
    @RolesAllowed("admin")
 //   @Authenticated
    @Produces("text/plain")
    @Path("adminName")
    public Uni<String> adminName() {
        LOG.info("Request received from client");

        return Uni.createFrom().item(principal.getName());
    }
}
