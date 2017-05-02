package com.example.bruno.exerfit;

/**
 * Created by bruno on 02/05/2017.
 */

public class Schedule {

    private Workout workout;
    private int dayOfTheWeek;

    public Schedule() {
    }

    public Schedule(Workout workout, int dayOfTheWeek) {
        this.workout = workout;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Workout getWorkout() {
        return workout;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
