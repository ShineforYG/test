package com.shine.java8.optional;

public class Address {

    /**
     * 街道
     */
    private String street;

    /**
     * 门牌
     */
    private String door;

    public String getStreet() {
        return street;
    }

    public String getDoor() {
        return door;
    }

    public Address(String street, String door) {
        this.street = street;
        this.door = door;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", door='" + door + '\'' +
                '}';
    }
}
