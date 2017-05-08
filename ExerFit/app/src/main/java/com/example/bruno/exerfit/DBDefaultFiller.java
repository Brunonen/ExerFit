package com.example.bruno.exerfit;

import android.content.Context;

/**
 * Created by bruno on 02/05/2017.
 */

public class DBDefaultFiller {

    private SQLWrapper sqlWrapper;

    public DBDefaultFiller(Context context){
        sqlWrapper = new SQLWrapper(context);
    }

    //THIS IS FOR TESTING! AND WILL ONLY BE CALLED ON INSTALLATION TO CREATE THE DATABASE!
    public void fillDataBaseWithDefaultData(){

        //First delete all data
        sqlWrapper.deleteAllSchedules();
        sqlWrapper.deleteAllWokroutXExercisesEntries();
        sqlWrapper.deleteAllWorkouts();
        sqlWrapper.deleteAllExercises();
        sqlWrapper.deleteAllTypes();
        sqlWrapper.deleteAllLocations();
        sqlWrapper.deleteAllCategories();

        //Fill with default DATA
        sqlWrapper.addCategory(new Category(0, "Biceps"));
        sqlWrapper.addCategory(new Category(0, "Triceps"));
        sqlWrapper.addCategory(new Category(0, "Chest"));
        sqlWrapper.addCategory(new Category(0, "Back"));
        sqlWrapper.addCategory(new Category(0, "Shoulder"));
        sqlWrapper.addCategory(new Category(0, "Traps"));
        sqlWrapper.addCategory(new Category(0, "Thighs"));
        sqlWrapper.addCategory(new Category(0, "Calves"));
        sqlWrapper.addCategory(new Category(0, "Abs"));
        sqlWrapper.addCategory(new Category(0, "Glutes"));

        sqlWrapper.addLocation(new Location(0, "Outdoors"));
        sqlWrapper.addLocation(new Location(0, "Home Training"));
        sqlWrapper.addLocation(new Location(0, "Gym"));
        sqlWrapper.addLocation(new Location(0, "Mixed"));

        sqlWrapper.addType(new Type(0, "Full Body Workout"));
        sqlWrapper.addType(new Type(0, "Split Workouts"));
        sqlWrapper.addType(new Type(0, "Single Muscle"));
        sqlWrapper.addType(new Type(0, "Cardio"));

        sqlWrapper.addExercise(new Exercise(0, "Crunches", "On the Floor Chrunch up", 3, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 30, 0,0,0));
        sqlWrapper.addExercise(new Exercise(0, "Push Ups", "On the Floor Push Up", 3, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(3), 20, 0,0,0));
    }


}
