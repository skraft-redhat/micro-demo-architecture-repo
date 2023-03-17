package org.acme.elytron.security.jpa;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/server")
public class GreetingResource {

    @GET
    @RolesAllowed("user")
    @Path("/userService")
    @Produces(MediaType.TEXT_PLAIN)
    public String userName(@Context SecurityContext securityContext) {
        return "Hello this is from Server. The user is: " + securityContext.getUserPrincipal().getName();
    }

    @GET
    @PermitAll
    @Path("/publicService")
    @Produces(MediaType.TEXT_PLAIN)
    public String publicResource() {
        return "public";
    }

    @GET
    @RolesAllowed("admin")
    @Path("/adminService")
    @Produces(MediaType.TEXT_PLAIN)
    public String adminResource(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }
}
