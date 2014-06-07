package com.scurran.domain;

/**
 * Created by Steven Curran on 01/06/14.
 */
public enum PostType implements MenuItem {

    FOR_SALE("For Sale"), WANTED("Wanted");

    private final String type;

    PostType(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }
}
