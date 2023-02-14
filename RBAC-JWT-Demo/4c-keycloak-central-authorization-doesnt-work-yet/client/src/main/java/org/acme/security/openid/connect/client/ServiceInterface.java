package org.acme.security.openid.connect.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.oidc.client.reactive.filter.OidcClientRequestReactiveFilter;

@RegisterRestClient
@RegisterProvider(OidcClientRequestReactiveFilter.class)
@Path("/server")
public interface ServiceInterface {

    @GET
    @Produces("text/plain")
    @Path("userService")
    String call_userService();
    
    @GET
    @Produces("text/plain")
    @Path("adminService")
    String call_adminService();
}
