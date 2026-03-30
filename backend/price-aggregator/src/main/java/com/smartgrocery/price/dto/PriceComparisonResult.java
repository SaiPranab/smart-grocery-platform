package com.smartgrocery.price.dto;

import java.io.Serializable;
import java.util.Map;

public class PriceComparisonResult implements Serializable {
    private String productName;
    private Map<String, Double> providerPrices; // e.g., {"Walmart": 3.99, "WholeFoods": 5.49}
    private String cheapestProvider;

    // Constructors
    public PriceComparisonResult() {}

    public PriceComparisonResult(String productName, Map<String, Double> providerPrices, String cheapestProvider) {
        this.productName = productName;
        this.providerPrices = providerPrices;
        this.cheapestProvider = cheapestProvider;
    }

    // Getters and Setters
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Map<String, Double> getProviderPrices() { return providerPrices; }
    public void setProviderPrices(Map<String, Double> providerPrices) { this.providerPrices = providerPrices; }
    public String getCheapestProvider() { return cheapestProvider; }
    public void setCheapestProvider(String cheapestProvider) { this.cheapestProvider = cheapestProvider; }
}
