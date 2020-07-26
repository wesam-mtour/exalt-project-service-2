package com.exalt.partssystem.model;


import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class Address {
    private String city;
    private String country;
    private String street;
    /*
    Mark a field to be indexed with type GEO_2DSPHERE
     */
    @GeoSpatialIndexed(name = "address.point", type = GeoSpatialIndexType.GEO_2DSPHERE )
    private GeoJsonPoint point;

    public Address() {
    }

    public Address(String country, String city, String street, GeoJsonPoint point) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.point = point;
    }
    public String getCountry() {
        return country;
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
