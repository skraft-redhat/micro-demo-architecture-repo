package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.oracle.svm.core.annotate.Inject;

import io.quarkus.security.Authenticated;

@Path("/greeting")
public class GreetingServer {

 //   @Inject
 //   JsonWebToken principal;

    @GET
    @Path("/englisch")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
 //       return "Hello to " + principal.getName();
            return "hello";
    }

    @GET
    @Path("deutsch")
    @Produces(MediaType.TEXT_PLAIN)
    public String hallo() {
 //       return "Hallo zu " + principal.getName();
        return "Hallo";

    }

    @GET
    @Path("franzoesisch")
    @Produces(MediaType.TEXT_PLAIN)
    public String bonjour() {
   //     return "Bonjour a " + principal.getName();
        return "Bonjour";

    }
}