package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO;

/**
 * Created by bruno on 02/05/2017.
 */

public class Category {

    private int categoryID;
    private String description;

    public Category(){

    }

    public Category(int categoryID, String description) {
        this.categoryID = categoryID;
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
