package org.jordi;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import org.jordi.client.TickerResource;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@QuarkusMain
public class CryptoConverterMain implements QuarkusApplication {
    @Inject
    TickerResource tickerResource;

    @Override
    public int run(String... args) throws Exception {
        if (args.length == 0) {
            System.err.println("Please provide at least one cryptocurrency symbol as argument (e.g., BTC ETH DOGE)");
            return 1;
        }
        List<String> currencies = Arrays.asList(args);
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger errorCount = new AtomicInteger(0);
        AtomicInteger successCount = new AtomicInteger(0);

        Multi.createFrom().iterable(currencies)
                .onItem().transformToUniAndMerge(currency ->
                        tickerResource.getTicker(currency)
                                .onItem().invoke(response -> {
                                    System.out.printf("[%s] SUCCESS: %s%n",
                                            Thread.currentThread().getName(),
                                            response);
                                    successCount.incrementAndGet();
                                })
                                .onFailure().invoke(throwable -> {
                                    System.err.printf("[%s] ERROR for %s: %s%n",
                                            Thread.currentThread().getName(),
                                            currency, throwable.getMessage());
                                    errorCount.incrementAndGet();
                                })
                                .onFailure().recoverWithNull() // Continue with other currencies even if one fails
                )
                .collect().asList()
                .subscribe().with(
                        results -> {
                            System.out.println("=".repeat(50));
                            System.out.printf("Completed: %d successful, %d errors%n",
                                    successCount.get(), errorCount.get());
                            latch.countDown();
                        },
                        failure -> {
                            System.err.println("Overall failure: " + failure.getMessage());
                            latch.countDown();
                        }
                );

        boolean completed = latch.await(15, TimeUnit.SECONDS);
        if (!completed) {
            System.err.println("Some requests timed out after 15 seconds");
            return 1;
        }

        return 0;
    }
}
