package com.example.bruno.exerfit;

/**
 * Created by bruno on 02/05/2017.
 */

public class Location {

    private int locationID;
    private String description;

    public Location(){

    }

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
