package org.acme;

import java.io.ByteArrayInputStream;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.logging.Logger;

@Path("/")
@ApplicationScoped
public class RecommendationResource {

    private static final String RESPONSE_STRING_FORMAT = "recommendation v1 from '%s': %d\n";

    private static final String RESPONSE_STRING_NOW_FORMAT = "recommendation v3 %s from '%s': %d\n";

    private final Logger logger = Logger.getLogger(RecommendationResource.class);

    Random random = new Random();

    /**
     * Counter to help us see the lifecycle
     */
    private int count = 0;

    /**
     * Flag for throwing a 503 when enabled
     */
    private boolean misbehave = false;

    private String HOSTNAME = System.getenv().getOrDefault("HOSTNAME", "unknown");

    @GET
    @Retry(maxRetries = 5)
    @Bulkhead(value=3)
    
    @Fallback(fallbackMethod = "getFallBackRecommendations")
    public Response getRecommendations() {

        count++;
        logger.info(String.format("recommendation request from %s: %d", HOSTNAME, count));

        // timeout();

        logger.debug("recommendation service ready to return");
        if (misbehave && random.nextBoolean()) {
            throw new RuntimeException("Resource failure.");
        }
        //return doMisbehavior();


        return Response.ok(String.format(RESPONSE_STRING_FORMAT, HOSTNAME, count)).build();
        // return Response.ok(String.format(RESPONSE_STRING_NOW_FORMAT, getNow(), HOSTNAME, count)).build();
    }

    public Response getFallBackRecommendations() {
        return Response.ok("Weiss nicht!").status(204).build();
    }

    private void timeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.info("Thread interrupted");
        }
    }

    private Response doMisbehavior() {
        logger.debug(String.format("Misbehaving %d", count));
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(String.format("recommendation misbehavior from '%s'\n", HOSTNAME)).build();
    }

    @GET
    @Path("/misbehave")
    public Response flagMisbehave() {
        this.misbehave = true;
        logger.debug("'misbehave' has been set to 'true'");
        return Response.ok("50% of the following requests to / will return a 503\n").build();
    }

    @GET
    @Path("/behave")
    public Response flagBehave() {
        this.misbehave = false;
        logger.debug("'misbehave' has been set to 'false'");
        return Response.ok("Following requests to / will return 200\n").build();
    }

    private String getNow() {
        final Client client = ClientBuilder.newClient();
        final Response res = client.target("http://worldclockapi.com/api/json/cet/now").request().get();
        final String jsonObject = res.readEntity(String.class);
        return Json.createReader(new ByteArrayInputStream(jsonObject.getBytes())).readObject().getString("currentDateTime");
    }

}