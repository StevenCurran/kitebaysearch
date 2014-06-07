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
    AIRUSH("Airush"),
    FLYSURFER("Flysurfer"),
    XENON("Xenon"),
    RPM("RPM"),
    NORTH("North"),
    SLINGSHOT("Slingshot"),
    HYPERLITE("Hyperlite"),
    LINES("Lines"),
    SHINN("Shinn"),
    MYSTIC("Mystic"),
    LIQUID_FORCE("Liquid Force"),
    BRUNOTTI("Brunotti"),
    WAINMAN("Wainman"),
    SCRUB("Scrub"),
    RRD("RRD"),
    NOBILE("NOBILE"),
    TAKOON("Takoon"),
    ION("ION"),
    CRAZYFLY("Crazyfly"),
    BLADE("Blade"),
    UNKNOWN("Brand Unknown");

    private final String brand;

    Brand(String brand) {
        this.brand = brand;
    }

    public String getBrandName() {
        return brand;
    }

    @Override
    public String toString() {
        return getBrandName();
    }
}

