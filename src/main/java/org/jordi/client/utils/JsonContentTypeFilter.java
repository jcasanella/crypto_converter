package org.jordi.client.utils;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class JsonContentTypeFilter implements ClientResponseFilter {
    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
            throws IOException {
        if ("text/json".equalsIgnoreCase(responseContext.getMediaType().toString())) {
            responseContext.getHeaders().putSingle("Content-Type", "application/json");
        }
    }
}
