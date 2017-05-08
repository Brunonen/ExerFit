package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO;

/**
 * Created by bruno on 02/05/2017.
 */

public class Exercise {

    private int exerciseID;
    private String name;
    private String description;
    private int intensity;
    private Location location;
    private Category category;
    private int defaultReps;
    private int defaultWeightKG;
    private int defaultWeightLBS;
    private int defaultDistanceM;

    public Exercise() {
    }

    public Exercise(int exerciseID, String name, String description, int intensity, Location location, Category category, int defaultReps, int defaultWeightKG, int defaultWeightLBS, int defaultDistanceM) {
        this.exerciseID = exerciseID;
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.location = location;
        this.category = category;
        this.defaultReps = defaultReps;
        this.defaultWeightKG = defaultWeightKG;
        this.defaultWeightLBS = defaultWeightLBS;
        this.defaultDistanceM = defaultDistanceM;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIntensity() {
        return intensity;
    }

    public Location getLocation() {
        return location;
    }

    public Category getCategory() {
        return category;
    }

    public int getDefaultReps() {
        return defaultReps;
    }

    public int getDefaultWeightKG() {
        return defaultWeightKG;
    }

    public int getDefaultWeightLBS() {
        return defaultWeightLBS;
    }

    public int getDefaultDistanceM() {
        return defaultDistanceM;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDefaultReps(int defaultReps) {
        this.defaultReps = defaultReps;
    }

    public void setDefaultWeightKG(int defaultWeightKG) {
        this.defaultWeightKG = defaultWeightKG;
    }

    public void setDefaultWeightLBS(int defaultWeightLBS) {
        this.defaultWeightLBS = defaultWeightLBS;
    }

    public void setDefaultDistanceM(int defaultDistanceM) {
        this.defaultDistanceM = defaultDistanceM;
    }
}
