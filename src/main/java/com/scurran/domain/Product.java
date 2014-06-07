package com.scurran.domain;

/**
 * Created by Steven Curran on 01/06/14.
 */
public enum Product implements MenuItem {


    FOIL("Foil"), LEI("Water"), RACE_BOARD("Race Board"), TT_BOARD("Twin Tip"), WAKE_BOARD("Wake Style"), SURF_BOARD("Surf"), WETSUIT("Wetsuit"), HARNESS("Harness"), ACCESSORY("Accessory"), BAR("Bar");

    private final String term;

    Product(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return getTerm();
    }
}
