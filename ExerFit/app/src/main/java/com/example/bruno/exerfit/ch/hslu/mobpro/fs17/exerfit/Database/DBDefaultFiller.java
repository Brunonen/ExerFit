package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database;

import android.content.Context;
import android.support.annotation.StringRes;

import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Category;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.DayOfTheWeek;
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

        sqlWrapper.addExercise(new Exercise(0, "Crunches", "On the Floor Chrunch up",
                3, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 30, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Push Ups", "On the Floor Push Up",
                3, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(3), 20, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Pull Ups Home",
                "On the bar, arms Stretched, lift yourself up until your forehead matches the height of the bar.", 5, sqlWrapper.getLocationByID(2),
                sqlWrapper.getCategoryByID(1), 10, 0 , 0 , 0));

        sqlWrapper.addExercise(new Exercise(0, "Pull Ups Gym",
                "On the bar, arms Stretched, lift yourself up until your forehead matches the height of the bar.", 5, sqlWrapper.getLocationByID(3),
                sqlWrapper.getCategoryByID(1), 10, 0 , 0 , 0));

        sqlWrapper.addExercise(new Exercise(0, "Bizep Curls",
                "1. Stand up straight with a dumbbell in each hand, at arm's length. Keep your elbows close to your torso " +
                        "and rotate the palms of your hands until they are facing forward. This will be your starting position.\n" +
                        "\n\n2. Now, keeping the upper arms stationary, exhale and curl the weights while contracting your biceps. Continue to raise the weights until " +
                        "your biceps are fully contracted and the dumbbells are at shoulder level. Hold the contracted position for a brief pause as you squeeze your biceps.\n" +
                        "\n\n3. Then, inhale and slowly begin to lower the dumbbells back to the starting position.\n" +
                        "\n\n4. Repeat for the recommended amount of repetitions", 6, sqlWrapper.getLocationByID(3),
                sqlWrapper.getCategoryByID(1), 12, 20, 40, 0));

        sqlWrapper.addExercise(new Exercise(0, "Barbell Curls",
                "1. Stand up with your torso upright while holding a barbell at a shoulder-width grip. " +
                        "The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position" +
                        "\n\n2. While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. " +
                        "Tip: Only the forearms should move." +
                        "\n\n3. Continue the movement until your biceps are fully contracted and the bar is at shoulder level. " +
                        "Hold the contracted position for a second and squeeze the biceps hard." +
                        "\n\n4. Slowly begin to bring the bar back to starting position as your breathe in."+
                        "\n\n5. Repeat for the recommended amount of repetitions", 7, sqlWrapper.getLocationByID(3),
                sqlWrapper.getCategoryByID(1), 12, 20, 40, 0));

        sqlWrapper.addExercise(new Exercise(0, "SitUp", "1. Lie down on the floor and secure your feet. Your legs should be bent at the knees.\n" +
                "\n2. Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.\n" +
                "\n3. Flex your hips and spine to raise your torso toward your knees.\n" +
                "\n4. At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only of the way down.\n" +
                "\n5. Repeat for the recommended amount of repetitions.", 4, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 30, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Alternate Heel Touchers", "1. Lie on the floor with the knees bent and the feet on the floor around 18-24 inches apart. Your arms should be extended by your side. This will be your starting position.\n" +
                "\n2. Crunch over your torso forward and up about 3-4 inches to the right side and touch your right heel as you hold the contraction for a second. Exhale while performing this movement.\n" +
                "\n3. Now go back slowly to the starting position as you inhale.\n" +
                "\n4. Now crunch over your torso forward and up around 3-4 inches to the left side and touch your left heel as you hold the contraction for a second. Exhale while performing this movement and then go back to the starting position as you inhale. Now that both heels have been touched, that is considered 1 repetition.\n" +
                "\n5. Continue alternating sides in this manner until all prescribed repetitions are done", 4, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 40, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Full Moon", "1. Start on the floor in a seated position with your hands placed behind you slightly wider than shoulder-distance apart. Extend your legs out in front of you, and lean back slightly. This will be your starting position.\n" +
                "\n2. Start by swinging your legs out to the side, then up towards your chest, and back down to the starting position. Essentially making a large circle. As you bring your legs towards you, lift your chest up, bringing it closer to your legs.\n" +
                "\n3. Alternate directions, moving clockwise to counterclockwise with each repetition. Try to keep your back straight and your abs engaged the entire time.\n" +
                "\n4. Complete for the recommended number of repetitions.\n" +
                "\n5. Tip: To make it easier, have a slight bend at the knees", 4, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 15, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Jackknife SitUp", "1. Lie flat on the floor (or exercise mat) on your back with your arms extended straight back behind your head and your legs extended also. This will be your starting position.\n" +
                "\n2. As you exhale, bend at the waist while simultaneously raising your legs and arms to meet in a jackknife position. Tip: The legs should be extended and lifted at approximately a 35-45 degree angle from the floor and the arms should be extended and parallel to your legs. The upper torso should be off the floor.\n" +
                "\n3. While inhaling, lower your arms and legs back to the starting position.\n" +
                "\n4. Repeat for the recommended amount of repetitions.", 6, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 15, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Oblique Crunches", "1. Start out by lying on your right side with your legs lying on top of each other. Make sure your knees are bent a little bit.\n" +
                "\n2. Place your left hand behind your head.\n" +
                "\n3. Once you are in this set position, begin by moving your left elbow up as you would perform a normal crunch except this time the main emphasis is on your obliques.\n" +
                "\n4. Crunch as high as you can, hold the contraction for a second and then slowly drop back down into the starting position.\n" +
                "\n5. Remember to breathe in during the eccentric (lowering) part of the exercise and to breathe out during the concentric (elevation) part of the exercise.", 5, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 20, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Plate Twist", "1. Lie down on the floor or an exercise mat with your legs fully extended and your upper body upright. Grab the plate by its sides with both hands out in front of your abdominals with your arms slightly bent.\n" +
                "\n2. Slowly cross your legs near your ankles and lift them up off the ground. Your knees should also be bent slightly. Note: Move your upper body back slightly to help keep you balanced turning this exercise. This is the starting position.\n" +
                "\n3. Move the plate to the left side and touch the floor with it. Breathe out as you perform that movement.\n" +
                "\n4. Come back to the starting position as you breathe in and then repeat the movement but this time to the right side of the body. Tip: Use a slow controlled movement at all times. Jerking motions can injure the back.\n" +
                "\n5. Repeat for the recommended amount of repetitions.", 3, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 60, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Leg Raises", "1. Lie down on the floor or an exercise mat with your legs fully extendet. This is your starting position\n" +
                "\n2. Now activate the Lower part of your Abs to pull your legs up. Keep your legs straight while doing so until your legs are in about 90 degree angel.\n"+
                "\n3. Lower your legs back down to the starting position\n" +
                "\n4. Repeat for the recommended amount of repetitions", 6, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 15, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Germans", "1. Liw down on the floor or an exercise mat and secure your feet. Cross your arms so your hands rest on the opposite sides shoulder. This is your starting position.\n"+
                "\n2. Now activate the lower region of your Abs to pull yourself upwards until you reach about a 45 degree angel.\n" +
                "\n3. Once you reach this position extend your arms out so they are streched and then get them back on your shoulders. Reapeat this 3 times.\n"+
                "\n4. Now slowly descend your upper-body back down to the starting position.\n"+
                "\n5. Repeat for the recommended amount of repetitions.", 6, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 15, 0,0,0));

        sqlWrapper.addExercise(new Exercise(0, "Air Bikes", "1. Lie flat on the floor with your lower back pressed to the ground. For this exercise, you will need to put your hands beside your head. Be careful however to not strain with the neck as you perform it. Now lift your shoulders into the crunch position.\n" +
                "\n2. Bring knees up to where they are perpendicular to the floor, with your lower legs parallel to the floor. This will be your starting position.\n" +
                "\n3. Now simultaneously, slowly go through a cycle pedal motion kicking forward with the right leg and bringing in the knee of the left leg. Bring your right elbow close to your left knee by crunching to the side, as you breathe out.\n" +
                "\n4. Go back to the initial position as you breathe in.\n" +
                "\n5. Crunch to the opposite side as you cycle your legs and bring closer your left elbow to your right knee and exhale.\n" +
                "\n6. Continue alternating in this manner until all of the recommended repetitions for each side have been completed.", 4, sqlWrapper.getLocationByID(2), sqlWrapper.getCategoryByID(9), 30, 0,0,0));

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
        exerciseListGym.add(sqlWrapper.getExerciseByID(6));

        testWorkoutGym.setExerciseList(exerciseListGym);

        Workout testWorkoutAbs = new Workout();
        testWorkoutAbs.setWorkoutID(0);
        testWorkoutAbs.setName("Full Ab Workout");
        testWorkoutAbs.setLocation(sqlWrapper.getLocationByID(2));
        testWorkoutAbs.setType(sqlWrapper.getTypeByID(3));
        testWorkoutAbs.setRestBetweenSets(0);
        testWorkoutAbs.setRestBetweenExercises(10);
        testWorkoutAbs.setSets(1);

        List<Exercise> exerciseListAbs= new ArrayList<Exercise>();
        exerciseListAbs.add(sqlWrapper.getExerciseByID(7));
        exerciseListAbs.add(sqlWrapper.getExerciseByID(11));
        exerciseListAbs.add(sqlWrapper.getExerciseByID(15));
        exerciseListAbs.add(sqlWrapper.getExerciseByID(8));
        exerciseListAbs.add(sqlWrapper.getExerciseByID(12));
        exerciseListAbs.add(sqlWrapper.getExerciseByID(13));
        exerciseListAbs.add(sqlWrapper.getExerciseByID(10));
        exerciseListAbs.add(sqlWrapper.getExerciseByID(14));

        exerciseListAbs.add(sqlWrapper.getExerciseByID(9));
        //Test for Custom Reps
        /*Exercise exec = sqlWrapper.getExerciseByID(9);
        exec.setDefaultReps(40);
        exerciseListAbs.add(exec);*/


        testWorkoutAbs.setExerciseList(exerciseListAbs);

        sqlWrapper.addWorkout(testWorkoutHome);
        sqlWrapper.addWorkout(testWorkoutGym);
        sqlWrapper.addWorkout(testWorkoutAbs);

        //ONLY FOR TEST!
        /*sqlWrapper.addSchedule(sqlWrapper.getWorkoutByID(3), DayOfTheWeek.MONDAY);
        sqlWrapper.addSchedule(sqlWrapper.getWorkoutByID(2), DayOfTheWeek.MONDAY);
        sqlWrapper.addSchedule(sqlWrapper.getWorkoutByID(1), DayOfTheWeek.TUESDAY);
        sqlWrapper.addSchedule(sqlWrapper.getWorkoutByID(3), DayOfTheWeek.TUESDAY);
        sqlWrapper.addSchedule(sqlWrapper.getWorkoutByID(2), DayOfTheWeek.FRIDAY);*/

        //FOR DEMO
        Workout demoWorkout = new Workout();
        demoWorkout.setWorkoutID(0);
        demoWorkout.setName("Demo Workout");
        demoWorkout.setLocation(sqlWrapper.getLocationByID(3));
        demoWorkout.setType(sqlWrapper.getTypeByID(1));
        demoWorkout.setRestBetweenSets(20);
        demoWorkout.setRestBetweenExercises(5);
        demoWorkout.setSets(2);

        List<Exercise> exerciseListDemo= new ArrayList<Exercise>();
        exerciseListDemo.add(sqlWrapper.getExerciseByID(1));
        exerciseListDemo.add(sqlWrapper.getExerciseByID(5));
        exerciseListDemo.add(sqlWrapper.getExerciseByID(8));

        demoWorkout.setExerciseList(exerciseListDemo);

        sqlWrapper.addWorkout(demoWorkout);

    }
}
