package com.wta.NewCloudApp.mvp.model.entity;

public class Location {
    public String street;
    public String city;
    public String country;

    public Location() {
    }

    public Location(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }
}
