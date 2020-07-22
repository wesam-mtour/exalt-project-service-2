package com.exalt.partssystem.model;


import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class Address {
    private String country;
    private String city;
    private String street;
    private GeoJsonPoint point;

    public Address() {
    }


    public String getCountry() {
        return country;
    }

    public Address(String country, String city, String street, GeoJsonPoint point) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.point = point;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public GeoJsonPoint getPoint() {
        return point;
    }

    public void setPoint(GeoJsonPoint point) {
        this.point = point;
    }
}
