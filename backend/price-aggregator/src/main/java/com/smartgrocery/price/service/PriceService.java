package com.smartgrocery.price.service;

import com.smartgrocery.price.dto.PriceComparisonResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PriceService {

    // @Cacheable intercepts the method call. If a value for this 'productName'
    // already exists in Redis under the "prices" bucket, it returns it instantly
    // without executing the code inside the method!
    @Cacheable(value = "prices", key = "#productName")
    public PriceComparisonResult comparePrices(String productName) {
        System.out.println("CACHE MISS! Fetching live prices from providers for: " + productName);

        // Simulate a slow network call to external APIs (3 seconds)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mocking the data we would normally get from external APIs
        Map<String, Double> prices = new HashMap<>();
        prices.put("ProviderA", Math.round((Math.random() * 5 + 2) * 100.0) / 100.0);
        prices.put("ProviderB", Math.round((Math.random() * 5 + 2) * 100.0) / 100.0);
        prices.put("ProviderC", Math.round((Math.random() * 5 + 2) * 100.0) / 100.0);

        // Find the cheapest
        String cheapest = prices.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return new PriceComparisonResult(productName, prices, cheapest);
    }
}
