package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;


@Path("/serviceB")
public class GreetingResource {
    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    @GET
    @Path("publicEP")
    @Produces(MediaType.TEXT_PLAIN)
    public String public_from_server() {
        LOG.info("Received request from client");

        return "Hello from Server. This is the publicService EP";
    }

    @GET
    @Path("userEP")
    @Produces(MediaType.TEXT_PLAIN)
    public String userService_from_server() {
        LOG.info("Received request from client");

        return "Hello from Server. This is the uesrService EP";
    }

    @GET
    @Path("adminEP")
    @Produces(MediaType.TEXT_PLAIN)
    public String AdminService_from_server() {
        LOG.info("Received request from client");

        return "Hello from Server. This is the adminService EP";
    }
}