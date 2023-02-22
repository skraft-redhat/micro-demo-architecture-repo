package org.acme.client;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.logging.Logger;

import com.oracle.svm.core.annotate.Inject;

import io.quarkus.security.identity.SecurityIdentity;

import java.util.Base64;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class RequestHeaderFactory implements ClientHeadersFactory {
    @Inject
    SecurityIdentity identity;
    
    private static final Logger LOG = Logger.getLogger(RequestHeaderFactory.class);

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders, MultivaluedMap<String, String> clientOutgoingHeaders) {
        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        
        result.add("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes()));
        return result;
    }

    
}
