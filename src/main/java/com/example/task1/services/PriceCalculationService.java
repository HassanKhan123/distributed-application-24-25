package com.example.task1.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Provides utility methods for price calculations, such as rounding prices
 * according to a custom rule (rounding up from 0.5).
 * 
 * <p>
 * This service centralizes rounding logic to ensure consistent calculations
 * throughout the application.
 * </p>
 */
@Service
public class PriceCalculationService {

    /**
     * Rounds the given price to the nearest integer value using a custom rule:
     * rounds up for fractional values of 0.5 or higher.
     *
     * @param price the price to be rounded (as a {@link BigDecimal})
     * @return the rounded price (as an integer)
     * @throws IllegalArgumentException if the input price is null
     */
    public int roundPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        // Rounds up for values >= 0.5
        return price.setScale(0, RoundingMode.HALF_UP).intValue();
    }
}
