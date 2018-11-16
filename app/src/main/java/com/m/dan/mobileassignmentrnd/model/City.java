package com.m.dan.mobileassignmentrnd.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class City implements Parcelable {


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

    public int get_id() {
        return _id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this.country);
        dest.writeString(this.name);
        dest.writeParcelable(this.coordinate, flags);
    }

    protected City(Parcel in) {
        this._id = in.readInt();
        this.country = in.readString();
        this.name = in.readString();
        this.coordinate = in.readParcelable(Coordinate.class.getClassLoader());
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
