package org.acme.security.openid.connect.server;

@Path("/server")
public class ProtectedResource {

    private static final Logger LOG = Logger.getLogger(ProtectedResource.class);

    @Inject
    JsonWebToken principal;

    @GET
    @Produces("text/plain")
    @Path("userService")
    public String userService() {
        LOG.info("Request received from client");
        return principal.getName();
    }
    
    @GET
    @Produces("text/plain")
    @Path("adminService")
    public String adminService() {
        LOG.info("Request received from client");
        return principal.getName();
    }
}
