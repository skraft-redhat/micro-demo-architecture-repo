package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.oidc.client.reactive.filter.OidcClientRequestReactiveFilter;

@RegisterRestClient(baseUri="http://localhost:9000")
@Produces(MediaType.TEXT_PLAIN)
//@RegisterProvider(OidcClientRequestReactiveFilter.class)
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
