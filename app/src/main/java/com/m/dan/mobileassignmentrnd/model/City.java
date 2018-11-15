package com.m.dan.mobileassignmentrnd.model;

import com.google.gson.annotations.SerializedName;

public class City {


    @SerializedName("_id")
    private int _id;
    @SerializedName("country")
    private String country;
    @SerializedName("name")
    private String name;
    @SerializedName("coord")
    private Coordinate coordinate;

    public City(int _id, String country, String name, Coordinate coordinate) {
        this._id = _id;
        this.country = country;
        this.name = name;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "City{" +
                "_id=" + _id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
