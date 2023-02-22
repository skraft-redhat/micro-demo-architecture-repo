package org.acme.security.openid.connect.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.oidc.client.reactive.filter.OidcClientRequestReactiveFilter;

@RegisterRestClient
@RegisterProvider(OidcClientRequestReactiveFilter.class)
@Path("/server")
public interface ServerInterface {

    @GET
    @Path("/userService")
    String userService();

    @GET
    @Path("/adminService")
    String adminService();

    @GET
    @Path("/publicService")
    String publicService();
}
