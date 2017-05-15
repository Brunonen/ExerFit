package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.DayOfTheWeek;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Schedule;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout_X_Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.DBDefaultFiller;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.ArrayList;
import java.util.List;

public class WorkoutScreen extends AppCompatActivity {

    static final int REST_TIME = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_screen);

        SQLWrapper sqlWrapper = new SQLWrapper(this);
        DBDefaultFiller db = new DBDefaultFiller(this);
        db.fillDataBaseWithDefaultData();

        Workout workout = sqlWrapper.getWorkoutByIDWithExercises(3);
        List<String> exerciseList = new ArrayList<>();
        for(Exercise exec : workout.getExerciseList()){
            exerciseList.add(exec.getName() + "\t\t | " + exec.getDefaultReps());
        }

        String[] myStringArray = new String[exerciseList.size()];
        myStringArray = exerciseList.toArray(myStringArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listitem_white_text, myStringArray);

        final String[] PROJECTION = new String[] {"Description",
                "Reps", "Difficulty"};

        ListView listView = (ListView) findViewById(R.id.List_View);
        listView.setAdapter(adapter);



        /*List<Category> catList = sqlWrapper.getAllCategories();

        for(Category cat : catList) {
            System.out.println("ID: " + cat.getCategoryID() + "  DESC: " + cat.getDescription());
        }

        List<Location> locList = sqlWrapper.getAllLocations();

        for(Location loc : locList) {
            System.out.println("ID: " + loc.getLocationID() + "  DESC: " + loc.getDescription());
        }

        List<Type> typeList = sqlWrapper.getAllTypes();

        for(Type type : typeList) {
            System.out.println("ID: " + type.getTypeID() + "  DESC: " + type.getDescription());
        }*/

        //List<Workout> workoutList = sqlWrapper.getWorkoutsByFilterObject(sqlWrapper.getTypeByID(1), sqlWrapper.getLocationByID(2));
        /*List<Workout> workoutList = sqlWrapper.getAllWorkouts();

        for(Workout workout : workoutList){
            String outString = "";
            outString += "ID: " + workout.getWorkoutID();
            outString += "\nName: " + workout.getName();
            outString += "\nLocation: " + workout.getLocation().getDescription();
            outString += "\nType: " + workout.getType().getDescription();
            outString += "\nSets: " + workout.getSets();
            outString += "\nRestBetweenSets: " + workout.getRestBetweenSets();
            outString += "\nRestBetweenExercises: " + workout.getRestBetweenExercises();
            outString += "\nExercises: ";

            for(Exercise exec : workout.getExerciseList()){
                outString += "\n\t" + exec.getName();
                //outString += "\n\n" + exec.getDescription();
            }

            System.out.println(outString);
        }*/

        /*List<Workout_X_Exercise> references = sqlWrapper.getAllWorkoutReferences();
        System.out.println(references);
        for(Workout_X_Exercise ref : references){
            System.out.println(ref.getWorkout().getName() + " " + ref.getExercise().getName());
        }*/

        /*List<Schedule> scheduleList = sqlWrapper.getAllSchedules();
        String outString = "";
        for(Schedule sched : scheduleList){
            outString += sched.getWorkout().getWorkoutID() + "\n";
            outString += sched.getWorkout().getName() + "\t\n";
            outString += sched.getDayOfTheWeek() +"\n\n";
        }

        System.out.println(outString);

        List<Schedule> scheduleListTuesday = sqlWrapper.getScheduleByDayOfTheWeek(DayOfTheWeek.TUESDAY);
        outString = "\n\n second \n\n";
        for(Schedule sched : scheduleListTuesday){
            outString += sched.getWorkout().getWorkoutID() + "\n";
            outString += sched.getWorkout().getName() + "\t\n";
            outString += sched.getDayOfTheWeek();
        }

        System.out.println(outString);*/

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startWorkout(View v){

        Chronometer cMeter = (Chronometer) findViewById(R.id.chronometer2);
        Button workOutBtn = (Button) findViewById(R.id.button2);
        if(workOutBtn.getText().equals("Start Workout")){
            cMeter.setBase(SystemClock.elapsedRealtime());
            cMeter.start();

            workOutBtn.setText("Set");
        }
        else if(workOutBtn.getText().equals("Set")){
            startRestTimer();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startRestTimer(){
        Chronometer cMeter = (Chronometer) findViewById(R.id.chronometer2);
        Button workOutBtn = (Button) findViewById(R.id.button2);
        cMeter.setCountDown(true);
        cMeter.start();

        workOutBtn.setText("Set");
        //workOutBtn.setEnabled(false);
    }
}
