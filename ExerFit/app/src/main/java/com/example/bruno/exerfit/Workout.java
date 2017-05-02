package com.example.bruno.exerfit;

/**
 * Created by bruno on 02/05/2017.
 */

public class Workout {

    private int workoutID;
    private String name;
    private Type type;
    private Location location;
    private int sets;
    private int restBetweenSets;
    private int restBetweenExercises;

    public Workout() {
    }

    public Workout(int workoutID, String name, Type type, Location location, int sets, int restBetweenSets, int restBetweenExercises) {
        this.workoutID = workoutID;
        this.name = name;
        this.type = type;
        this.location = location;
        this.sets = sets;
        this.restBetweenSets = restBetweenSets;
        this.restBetweenExercises = restBetweenExercises;
    }

    public int getWorkoutID() {
        return workoutID;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public int getSets() {
        return sets;
    }

    public int getRestBetweenSets() {
        return restBetweenSets;
    }

    public int getRestBetweenExercises() {
        return restBetweenExercises;
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setRestBetweenSets(int restBetweenSets) {
        this.restBetweenSets = restBetweenSets;
    }

    public void setRestBetweenExercises(int restBetweenExercises) {
        this.restBetweenExercises = restBetweenExercises;
    }
}
