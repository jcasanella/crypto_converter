package org.jordi.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jordi.client.utils.TextJsonMessageBodyReader;
import org.jordi.model.CryptoResponse;

@Path("/ticker")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("text/json")
@RegisterProvider(TextJsonMessageBodyReader.class)
@RegisterRestClient
public interface TickerService {
    @GET
    @Path("/{crypto}/USD")
    Uni<CryptoResponse> getByCrypto(@PathParam("crypto") String crypto);
}
