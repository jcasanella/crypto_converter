package org.jordi;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.jordi.client.TickerResource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@QuarkusMain
public class CryptoConverterMain implements QuarkusApplication {
    @Inject
    TickerResource tickerResource;

    @Override
    public int run(String... args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        tickerResource.getTicker("BTC")
                .subscribe().with(
                        response -> {
                            System.out.println("=== SUCCESS ===");
                            System.out.println("Response: " + response);
                            System.out.println("===============");
                            latch.countDown();
                        },
                        failure -> {
                            System.err.println("=== FAILURE ===");
                            System.err.println("Error: " + failure.getMessage());
                            failure.printStackTrace();
                            System.err.println("===============");
                            latch.countDown();
                        }
                );

        boolean completed = latch.await(10, TimeUnit.SECONDS);
        if (!completed) {
            System.err.println("Request timed out after 10 seconds");
            return 1;
        }

        return 0;
    }
}
