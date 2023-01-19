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
        String token = generateJWTToken();
        LOG.info("adding Authorization: Bearer " + token);
        
        result.add("Authorization", "Bearer "+token);
        return result;
    }

    private String generateJWTToken() {
        LOG.info("Generating token");
        String token =
           Jwt.issuer("https://example.com/issuer") 
             .upn("jdoe@quarkus.io") 
             .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
             .claim(Claims.birthdate.name(), "2001-07-13")
             .expiresIn(3600)
           .sign();
        LOG.info("Generated Token: " + token);
        return token;
    }
}