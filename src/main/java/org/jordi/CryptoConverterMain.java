package org.jordi;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.jordi.client.TickerResource;

@QuarkusMain
public class CryptoConverterMain implements QuarkusApplication {
    @Inject
    TickerResource tickerResource;

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello ");
        var resp = tickerResource.getTicker("BTC");
        System.out.println("Response: " + resp.toString());
        return 0;
    }
}
