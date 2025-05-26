package org.jordi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoResponse {
    public String timestamp;
    public String low;
    public String hig;
    public String last;
    public String volume;
    public String volume30d;
    public double bid;
    public double ask;
    public String priceChange;
    public String priceChangePercentage;
    public String pair;
}
