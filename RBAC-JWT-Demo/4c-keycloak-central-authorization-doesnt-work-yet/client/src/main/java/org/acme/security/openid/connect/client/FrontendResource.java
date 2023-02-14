package org.acme.security.openid.connect.client;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/client")
public class FrontendResource {
    @Inject
    @RestClient
    ServiceInterface server;
    
    @GET
    @Path("userService")
    @Produces("text/plain")
    public String userService() {
        return server.call_userService();
    }
    
    @GET
    @Path("adminService")
    @Produces("text/plain")
    public String adminService() {
	    return server.call_adminService();
    }
    
}
