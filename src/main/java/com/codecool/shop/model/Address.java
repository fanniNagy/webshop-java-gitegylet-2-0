package com.codecool.shop.model;

public class Address {
    private String country;
    private String city;
    private int zipCode;
    private String streetAddress;
    private String optionalAddress;

    public Address(String country, String city, int zipCode, String streetAddress) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.streetAddress = streetAddress;
    }

    public void setOptionalAddress(String optionalAddress) {
        this.optionalAddress = optionalAddress;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getOptionalAddress() {
        return optionalAddress;
    }
}
