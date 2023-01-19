package org.acme;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/secured")
public class ClientGreetingResource {
    
    @Inject
    @RestClient
    SecuredGreetingFromServer greetingService;


    @GET
    public String getGreeting() {
//        return "Hello";
    return greetingService.getSecuredGreetingFromServer();
    }

    
}