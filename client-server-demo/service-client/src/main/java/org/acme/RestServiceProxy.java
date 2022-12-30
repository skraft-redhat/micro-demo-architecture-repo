package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Produces(MediaType.TEXT_PLAIN)
@ClientHeaderParam(name = "Authorization", value = "Bearer ${token}") 
@Path("/greeting")
public interface RestServiceProxy {

    @GET
    @Path("/deutsch")
    String getDeutsch();

    @GET
    @Path("/franzoesisch")
    String getFranzoesisch();

    @GET
    @Path("/englisch")
    String getEnglisch();
}
