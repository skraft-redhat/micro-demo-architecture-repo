package org.acme;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class PreferenceResource {

    private static final String RESPONSE_STRING_FORMAT = "preference => %s\n";

    private final Logger logger = Logger.getLogger(PreferenceResource.class);

    @Inject
    @RestClient
    RecommendationService recommendationService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "performedRequests", description = "How many primality checks have been performed.")
    @Timed(name = "checksTimer", description = "A measure of how long it takes to perform the primality test.", unit = MetricUnits.MILLISECONDS)


    public Response getPreference() {
        try {
            this.delayArtificially();
            String response = recommendationService.getRecommendation();

            logger.info(String.format("Preference Service hit!"));
            return Response.ok(String.format(RESPONSE_STRING_FORMAT, response)).build();
        } catch (WebApplicationException ex) {
            Response response = ex.getResponse();
            logger.warn("Non HTTP 20x trying to get the response from recommendation service: " + response.getStatus());
            ex.printStackTrace();
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(String.format(RESPONSE_STRING_FORMAT,
                            String.format("Error: %d - %s", response.getStatus(), response.readEntity(String.class))))
                    .build();
        } catch (ProcessingException ex) {
            logger.warn("Exception trying to get the response from recommendation service.", ex);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(String.format(RESPONSE_STRING_FORMAT,
                    ex.getCause().getClass().getSimpleName() + ": " + ex.getCause().getMessage())).build();
        }
    }

    public void delayArtificially() {
        try {
            Thread.sleep(1000);
            logger.info("Artificial delay for 1s");
        } catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}