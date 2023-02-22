package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/GreetingService")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/admin")
    public String helloAdmin(@QueryParam("role") String role) {
        if (role.equals("admin")) {
            return "I greet you because you are a admin!";
        } else {
            return "I don't know you!";
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user")
    public String helloUser(@QueryParam("role") String role) {
        if (role.equals("user")) {
            return "I greet you because you are a user!";
        } else {
            return "I don't know you!";
        }
    }

}