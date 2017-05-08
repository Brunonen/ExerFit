package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database;

import android.content.Context;

import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Category;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Location;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Type;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;

import java.util.ArrayList;
import java.util.List;

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
        /*sqlWrapper.deleteAllSchedules();
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

        sqlWrapper.addExercise(new Exercise(0, "Crunches", "On the Floor Chrunch up",
                3, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 30, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Push Ups", "On the Floor Push Up",
                3, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(3), 20, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Pull Ups (Home)",
                "On the bar, arms Stretched, lift yourself up until your forehead matches the height of the bar.", 5, sqlWrapper.getLocationByID(2),
                sqlWrapper.getCategoryByID(1), 10, 0 , 0 , 0));

        sqlWrapper.addExercise(new Exercise(0, "Pull Ups (Gym)",
                "On the bar, arms Stretched, lift yourself up until your forehead matches the height of the bar.", 5, sqlWrapper.getLocationByID(3),
                sqlWrapper.getCategoryByID(1), 10, 0 , 0 , 0));

        sqlWrapper.addExercise(new Exercise(0, "Bizep Curls",
                "Use Curlbar, add weight. Get yourself in position to keep your arms streched. Now curl upwards only bending " +
                        "your elbows. Pay attention not to lift your arms but only snap your elbows.", 6, sqlWrapper.getLocationByID(3),
                sqlWrapper.getCategoryByID(1), 12, 20, 40, 0));

        Workout testWorkoutHome = new Workout();
        testWorkoutHome.setWorkoutID(0);
        testWorkoutHome.setName("Small Home Workout");
        testWorkoutHome.setLocation(sqlWrapper.getLocationByID(2));
        testWorkoutHome.setType(sqlWrapper.getTypeByID(1));
        testWorkoutHome.setRestBetweenSets(120);
        testWorkoutHome.setRestBetweenExercises(30);
        testWorkoutHome.setSets(3);

        List<Exercise> exerciseListHome = new ArrayList<Exercise>();
        exerciseListHome.add(sqlWrapper.getExerciseByID(1));
        exerciseListHome.add(sqlWrapper.getExerciseByID(2));
        exerciseListHome.add(sqlWrapper.getExerciseByID(3));

        testWorkoutHome.setExerciseList(exerciseListHome);

        Workout testWorkoutGym = new Workout();
        testWorkoutGym.setWorkoutID(0);
        testWorkoutGym.setName("Small Bizeps Workout");
        testWorkoutGym.setLocation(sqlWrapper.getLocationByID(3));
        testWorkoutGym.setType(sqlWrapper.getTypeByID(3));
        testWorkoutGym.setRestBetweenSets(120);
        testWorkoutGym.setRestBetweenExercises(30);
        testWorkoutGym.setSets(3);

        List<Exercise> exerciseListGym = new ArrayList<Exercise>();
        exerciseListGym.add(sqlWrapper.getExerciseByID(4));
        exerciseListGym.add(sqlWrapper.getExerciseByID(5));

        testWorkoutGym.setExerciseList(exerciseListGym);

        sqlWrapper.addWorkout(testWorkoutHome);
        sqlWrapper.addWorkout(testWorkoutGym);*/

    }
}
