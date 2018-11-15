package com.m.dan.mobileassignmentrnd.model;

import com.google.gson.annotations.SerializedName;

public class Coordinate {

    @SerializedName("lon")
    private float longitude;
    @SerializedName("lat")
    private float latitude;


    public Coordinate(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
