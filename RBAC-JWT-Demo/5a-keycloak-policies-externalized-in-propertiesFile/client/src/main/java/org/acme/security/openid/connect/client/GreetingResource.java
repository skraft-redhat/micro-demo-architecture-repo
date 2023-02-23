package org.acme.security.openid.connect.client;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.jboss.logging.Logger;

@Path("/client")
public class GreetingResource {
    @Inject
    @RestClient
    ServerInterface serverInterface;

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);
    
    @GET
    @Path("localService")
    @Produces(MediaType.TEXT_PLAIN)
    public String calling_localService() {

        LOG.info("Received request - forwarding to server");
 //       return serverInterface.publicService();
        return "hallo";
    }
    @GET
    @Path("publicService")
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
 //   @NoCache
    public String calling_AdminService() {
        LOG.info("Received request - forwarding to server");
        return serverInterface.adminService();
    }
}
