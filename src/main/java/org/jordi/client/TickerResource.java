package org.jordi.client;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jordi.model.CryptoResponse;

@ApplicationScoped
@Path("/ticker")
public class TickerResource {
    @RestClient
    TickerService tickerService;

    @GET
    @Path("/{crypto}/USD")
    public Uni<CryptoResponse> getTicker(@PathParam("crypto") String crypto) {
        return tickerService.getByCrypto(crypto);
    }
}
