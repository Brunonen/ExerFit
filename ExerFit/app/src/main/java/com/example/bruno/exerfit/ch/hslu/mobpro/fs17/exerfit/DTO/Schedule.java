package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO;

/**
 * Created by bruno on 02/05/2017.
 */

public class Schedule {

    private Workout workout;
    private DayOfTheWeek dayOfTheWeek;

    public Schedule() {
    }

    public Schedule(Workout workout, DayOfTheWeek dayOfTheWeek) {
        this.workout = workout;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Workout getWorkout() {
        return workout;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = DayOfTheWeek.valueOfInt(dayOfTheWeek);
    }

    public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
