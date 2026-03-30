package com.smartgrocery.price.controller;

import com.smartgrocery.price.dto.PriceComparisonResult;
import com.smartgrocery.price.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/compare")
    public ResponseEntity<PriceComparisonResult> compare(@RequestParam String productName) {
        return ResponseEntity.ok(priceService.comparePrices(productName));
    }
}
