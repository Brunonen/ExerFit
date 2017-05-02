package com.example.bruno.exerfit;

/**
 * Created by bruno on 02/05/2017.
 */

public class Workout_X_Exercise {

    private Workout workout;
    private Exercise exercise;
    private int customReps;
    private int customWeightKG;
    private int customWeightLBS;
    private int customDistanceM;

    public Workout_X_Exercise() {
    }

    public Workout_X_Exercise(Workout workout, Exercise exercise, int customReps, int customWeightKG, int customWeightLBS, int customDistanceM) {
        this.workout = workout;
        this.exercise = exercise;
        this.customReps = customReps;
        this.customWeightKG = customWeightKG;
        this.customWeightLBS = customWeightLBS;
        this.customDistanceM = customDistanceM;
    }

    public Workout getWorkout() {
        return workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getCustomReps() {
        return customReps;
    }

    public int getCustomWeightKG() {
        return customWeightKG;
    }

    public int getCustomWeightLBS() {
        return customWeightLBS;
    }

    public int getCustomDistanceM() {
        return customDistanceM;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setCustomReps(int customReps) {
        this.customReps = customReps;
    }

    public void setCustomWeightKG(int customWeightKG) {
        this.customWeightKG = customWeightKG;
    }

    public void setCustomWeightLBS(int customWeightLBS) {
        this.customWeightLBS = customWeightLBS;
    }

    public void setCustomDistanceM(int customDistanceM) {
        this.customDistanceM = customDistanceM;
    }
}
