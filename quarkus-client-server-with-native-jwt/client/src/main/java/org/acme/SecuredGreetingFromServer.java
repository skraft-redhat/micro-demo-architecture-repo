package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/secured-greeting")
@RegisterRestClient(baseUri="http://localhost:9000")
public interface SecuredGreetingFromServer {
    
    @GET
    String getSecuredGreetingFromServer();
    

}
