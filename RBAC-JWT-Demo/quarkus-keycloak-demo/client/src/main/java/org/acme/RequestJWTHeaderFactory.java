package org.acme;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.logging.Logger;

import io.smallrye.jwt.build.Jwt;

import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class RequestJWTHeaderFactory implements ClientHeadersFactory {
    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(RequestJWTHeaderFactory.class);


    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders, MultivaluedMap<String, String> clientOutgoingHeaders) {
        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        String token;
        token=jwt.getRawToken();
        LOG.info("adding Authorization: Bearer " + token);
        
        result.add("Authorization", "Bearer "+ token);
        return result;
    }

}