package org.jordi.client;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jordi.model.CryptoResponse;

@Path("/ticker")
@Produces("application/json")
@Consumes({"text/json"})
@RegisterRestClient
public interface TickerService {
    @GET
    @Path("/{crypto}/USD")
    CryptoResponse getByCrypto(@PathParam("crypto") String crypto);
}
