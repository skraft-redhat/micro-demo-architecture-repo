package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/")
public class CustomerResource {

    private static final String RESPONSE_STRING_FORMAT = "customer => %s\n";

    private final Logger logger = Logger.getLogger(CustomerResource.class);

    @Inject
    @RestClient
    PreferenceService preferenceService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCustomer() {
        try {
            String response = preferenceService.getPreference().trim();
            logger.info(String.format("Customer Service hit!"));
            return Response.ok(String.format(RESPONSE_STRING_FORMAT, response)).build();
        } catch (WebApplicationException ex) {
            Response response = ex.getResponse();
            logger.warn("Non HTTP 20x trying to get the response from preference service: " + response.getStatus());
            return Response
                    .status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(String.format(RESPONSE_STRING_FORMAT,
                            String.format("Error: %d - %s", response.getStatus(), response.readEntity(String.class)))
                    )
                    .build();
        } catch (ProcessingException ex) {
            logger.warn("Exception trying to get the response from preference service.", ex);
            return Response
                    .status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(String.format(RESPONSE_STRING_FORMAT, ex.getCause().getClass().getSimpleName() + ": " + ex.getCause().getMessage()))
                    .build();
        }
    }

}