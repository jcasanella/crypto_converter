package org.jordi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoResponse {
    public String timestamp;
    public String low;
    public String high;
    public String last;
    public String volume;
    public String volume30d;
    public double bid;
    public double ask;
    public String priceChange;
    public String priceChangePercentage;
    public String pair;

    @Override
    public String toString() {
        return String.format(" { timestamp: %s, low: %s, hig: %s, last: %s, volume: %s, volume30d: %s, bid: %.6f, ask: %.6f, priceChange: %s, " +
                        "priceChangePercentage: %s, pair: %s }",
                timestamp, low, high, last, volume, volume30d, bid, ask, priceChange, priceChangePercentage, pair);
    }
}
