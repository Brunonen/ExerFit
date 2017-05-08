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

    public void fillDataBaseWithDefaultData(){
        /*sqlWrapper.addCategory(new Category(0, "Full Body Workout"));
        sqlWrapper.addCategory(new Category(0, "Split Workouts"));
        sqlWrapper.addCategory(new Category(0, "Single Muscle"));
        sqlWrapper.addCategory(new Category(0, "Cardio"));

        sqlWrapper.addLocation(new Location(0, "Outdoors"));
        sqlWrapper.addLocation(new Location(0, "Home Training"));
        sqlWrapper.addLocation(new Location(0, "Gym"));
        sqlWrapper.addLocation(new Location(0, "Mixed"));

        sqlWrapper.addType(new Type(0, "Biceps"));
        sqlWrapper.addType(new Type(0, "Triceps"));
        sqlWrapper.addType(new Type(0, "Chest"));
        sqlWrapper.addType(new Type(0, "Back"));
        sqlWrapper.addType(new Type(0, "Shoulder"));
        sqlWrapper.addType(new Type(0, "Traps"));
        sqlWrapper.addType(new Type(0, "Thighs"));
        sqlWrapper.addType(new Type(0, "Calves"));
        sqlWrapper.addType(new Type(0, "Abs"));
        sqlWrapper.addType(new Type(0, "Glutes"));*/

        sqlWrapper.addExercise(new Exercise(0, "Crunches", "On the Floor Chrunch", 4, sqlWrapper.getLocationByID(2), ));
    }


}
