package com.scurran.domain;

/**
 * Created by Steven Curran on 01/06/14.
 */
public enum Brand {

    NAISH("Naish"),
    BEST("Best"),
    FLEXIFOIL("Flexifoil"),
    FONE("F-One"),
    OZONE("Ozone"),
    CABRINHA("Cabrinha"),
    AIRRUSH("AIRRUSH");

    private final String brand;

    Brand(String brand) {
        this.brand = brand;
    }

    public String getBrandName() {
        return brand;
    }
}
