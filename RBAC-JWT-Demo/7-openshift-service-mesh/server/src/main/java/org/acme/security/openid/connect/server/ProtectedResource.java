package org.acme.security.openid.connect.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;

@Path("/protected")
public class ProtectedResource {

    private static final Logger LOG = Logger.getLogger(ProtectedResource.class);

    @GET
    @Path("userName")
    public Uni<String> userName() {
        LOG.info("Request received from client");
        return Uni.createFrom().item("Hello from user:server");
    }
    
    @GET
    @Path("adminName")

    public Uni<String> adminName() {
        LOG.info("Request received from client");
        return Uni.createFrom().item("Hello from admin:server");
    }
}
