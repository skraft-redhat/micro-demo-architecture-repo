package com.redhat.developer.demos.recommendation.rest;

import java.io.ByteArrayInputStream;
import java.util.Random;

import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/")
public class RecommendationResource {

    private static final String RESPONSE_STRING_FORMAT = "recommendation v1 from '%s': %d\n";

    private static final String RESPONSE_STRING_NOW_FORMAT = "recommendation v3 %s from '%s': %d\n";

    private final Logger logger = Logger.getLogger(getClass());


    /**
     * Counter to help us see the lifecycle
     */
    private int count = 0;

    /**
     * Flag for throwing a 503 when enabled
     */
    private boolean misbehave = false;

    private boolean timeout = false;

    private boolean crash= false;



    private String HOSTNAME = System.getenv().getOrDefault("HOSTNAME", "unknown");

    @GET
    public Response getRecommendations() {

        count++;
        logger.info(String.format("recommendation request from %s: %d", HOSTNAME, count));

        // timeout();

        logger.debug("recommendation service ready to return");

        
            
        if (timeout) {
             try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                logger.info("Thread interrupted");
            }
        }


        if (crash) {
            crash();
         }
        if (misbehave) {
            return doMisbehavior();
        }
        return Response.ok(String.format(RESPONSE_STRING_FORMAT, HOSTNAME, count)).build();
        // return Response.ok(String.format(RESPONSE_STRING_NOW_FORMAT, getNow(), HOSTNAME, count)).build();
    }

    @GET
    @Path("/crash")
    public Response flagCrash() {
        this.crash = !this.crash;
        logger.debug("'crash' has been set to " + crash);
        return Response.ok("'crash' has been set to " + crash+"\n").build();
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
        return Response.ok("Following requests to / will return a 503\n").build();
    }

    @GET
    @Path("/behave")
    public Response flagBehave() {
        this.misbehave = false;
        logger.debug("'misbehave' has been set to 'false'");
        return Response.ok("Following requests to / will return 200\n").build();
    }

    @GET
    @Path("/timeout")
    public Response flagTimeout() {
        this.timeout = !this.timeout;
        logger.debug("'timeout' has been set to " + timeout);
        return Response.ok("'timeout' has been set to " + timeout+"\n").build();
    }


    private void crash() {
        logger.error("Crashing!");
        throw new RuntimeException("Resource failure.");
    }

}