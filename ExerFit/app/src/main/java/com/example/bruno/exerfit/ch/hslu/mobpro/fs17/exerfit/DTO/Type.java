package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO;

/**
 * Created by bruno on 02/05/2017.
 */

public class Type {

    private int typeID;
    private String description;

    public Type(){

    }

    public Type(int typeID, String description) {
        this.typeID = typeID;
        this.description = description;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getDescription() {
        return description;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
