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
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.DBDefaultFiller;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.List;

public class WorkoutScreen extends AppCompatActivity {

    static final int REST_TIME = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_screen);
        String[] myStringArray = new String[2];
        myStringArray[0] = "Sit-Ups     | 30";
        myStringArray[1] = "Push-Ups    | 15";


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listitem_white_text, myStringArray);

        final String[] PROJECTION = new String[] {"Description",
                "Reps", "Difficulty"};

        ListView listView = (ListView) findViewById(R.id.List_View);
        listView.setAdapter(adapter);

        SQLWrapper sqlWrapper = new SQLWrapper(this);
        DBDefaultFiller db = new DBDefaultFiller(this);
        db.fillDataBaseWithDefaultData();

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

        List<Exercise> exerciseList = sqlWrapper.getAllExercises();

        for(Exercise exec : exerciseList){
            String outString = "";
            outString += "ID: " + exec.getExerciseID();
            outString += "\nName: " + exec.getName();
            outString += "\nDESC: " + exec.getDescription();
            outString += "\nLocation: " + exec.getLocation().getDescription();
            outString += "\nCategory: " + exec.getCategory().getDescription();
            outString += "\nIntensity: " + exec.getIntensity();
            outString += "\nDefaultReps: " + exec.getDefaultReps();
            outString += "\nDefaultKG: " + exec.getDefaultWeightKG();
            outString += "\nDEaultLBS: " + exec.getDefaultWeightLBS();
            outString += "\nDefaultDistanceM: " + exec.getDefaultDistanceM();

            System.out.println(outString);
        }
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
        workOutBtn.setEnabled(false);
    }
}