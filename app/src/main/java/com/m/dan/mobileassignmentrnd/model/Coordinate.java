package com.m.dan.mobileassignmentrnd.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Coordinate implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.longitude);
        dest.writeFloat(this.latitude);
    }

    protected Coordinate(Parcel in) {
        this.longitude = in.readFloat();
        this.latitude = in.readFloat();
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public static final Parcelable.Creator<Coordinate> CREATOR = new Parcelable.Creator<Coordinate>() {
        @Override
        public Coordinate createFromParcel(Parcel source) {
            return new Coordinate(source);
        }

        @Override
        public Coordinate[] newArray(int size) {
            return new Coordinate[size];
        }
    };
}
