package com.mgarciareimers.someApp.model;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geolocation geo;

    public Address(String street, String suite, String city, String zipcode, Geolocation geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return this.suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geolocation getGeo() {
        return this.geo;
    }

    public void setGeo(Geolocation geo) {
        this.geo = geo;
    }

    public boolean containsFilter(String filter) {
        return this.street.contains(filter) || this.suite.contains(filter) || this.city.contains(filter) || this.zipcode.contains(filter);
    }
}
