package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Adapters.CustomAdapterExercises;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Adapters.DrawableResourceScanner;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.RunnableFuture;

public class WorkoutFragment extends Fragment {

    Workout selectedWorkout;
    SQLWrapper sqlWrapper;
    String selectedWorkoutString;
    String exerciseTimerString;
    TextView setsOutOfSets;
    TextView currentExerciseName;
    TextView currentExerciseExtra;
    TextView currentExerciseReps;
    TextView nextExerciseName;
    TextView nextExerciseExtra;
    TextView nextExerciseReps;
    TextView workoutTimer;
    Exercise currentExercise;
    Exercise nextExercise;
    Button workoutButton;
    Thread workoutTimerThread;
    Thread exerciseTimerThread;
    ImageView exerciseImage;
    int exerciseDuration;
    int workoutDuration;
    int exerciseCount;
    int setsDone = 1;
    DrawableResourceScanner imageResources;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_workout_fragment, container, false);

        sqlWrapper = new SQLWrapper(getActivity());

        MainActivity activity = (MainActivity) getActivity();
        selectedWorkoutString = activity.getSelectedWorkout();
        selectedWorkout = sqlWrapper.getWorkoutByNameWithExercises(selectedWorkoutString);

        if(!selectedWorkout.getName().equals("")) {
            imageResources = new DrawableResourceScanner(getActivity());
            exerciseImage = (ImageView) root.findViewById(R.id.imageView3);

            //testView.setImageResource(imageResources.getImageResourceByName("intensitybar_4"));
            final List<Exercise> exercises = selectedWorkout.getExerciseList();

            setsOutOfSets = (TextView) root.findViewById(R.id.textViewSetsOutOfSets);
            currentExerciseName = (TextView) root.findViewById(R.id.doWorkoutCurrentExerciseName);
            currentExerciseExtra = (TextView) root.findViewById(R.id.doWorkoutCurrentExerciseExtra);
            currentExerciseReps = (TextView) root.findViewById(R.id.doWorkoutCurrentExerciseReps);
            nextExerciseName = (TextView) root.findViewById(R.id.doWorkoutNextExerciseName);
            nextExerciseExtra = (TextView) root.findViewById(R.id.doWorkoutNextExerciseExtra);
            nextExerciseReps = (TextView) root.findViewById(R.id.doWorkoutNextExerciseReps);
            workoutTimer = (TextView) root.findViewById(R.id.textViewWorkoutTimer);

            updateSetCount();
            exerciseCount = 0;

            currentExercise = selectedWorkout.getExerciseList().get(exerciseCount);
            exerciseImage.setImageResource(imageResources.getImageResourceByName(getPictureNameForExercise(currentExercise.getName())));

            if (selectedWorkout.getExerciseList().size() > 1) {
                nextExercise = selectedWorkout.getExerciseList().get((exerciseCount + 1) % (selectedWorkout.getExerciseList().size()));
            } else {
                if (setsDone != (selectedWorkout.getSets() + 1)) {
                    nextExercise = selectedWorkout.getExerciseList().get(exerciseCount);
                } else {
                    nextExercise = new Exercise();
                }
            }

            updateExercises();

            workoutButton = (Button) root.findViewById(R.id.setAndWorkoutButton);
            workoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (workoutButton.getText().equals("Finish Workout")) {
                        stopExerciseTimerThread();
                        workoutTimerThread.interrupt();
                        System.out.println("Congratulations you finished your workout in : " + workoutDuration / 60 + " Minute  " + workoutDuration % 60 + "seconds");
                    }
                    if (workoutButton.getText().equals("Set")) {
                        if (setsDone == selectedWorkout.getSets() && exerciseCount == (selectedWorkout.getExerciseList().size() - 2)) {
                            startRestTimer(selectedWorkout.getRestBetweenExercises());
                            currentExercise = selectedWorkout.getExerciseList().get(exerciseCount + 1);

                            exerciseImage.setImageResource(imageResources.getImageResourceByName(getPictureNameForExercise(currentExercise.getName())));

                            nextExercise = new Exercise();
                            workoutButton.setText("Finish Workout");
                            updateExercises();

                        } else {

                            exerciseCount++;
                            exerciseCount = exerciseCount % selectedWorkout.getExerciseList().size();

                            if (exerciseCount == 0) {
                                setsDone++;
                                if (setsDone != (selectedWorkout.getSets() + 1)) {
                                    updateSetCount();
                                    startRestTimer(selectedWorkout.getRestBetweenSets());
                                }
                            } else {
                                startRestTimer(selectedWorkout.getRestBetweenExercises());
                            }

                            if (setsDone != (selectedWorkout.getSets() + 1)) {
                                currentExercise = selectedWorkout.getExerciseList().get(exerciseCount);
                                exerciseImage.setImageResource(imageResources.getImageResourceByName(getPictureNameForExercise(currentExercise.getName())));

                                if (setsDone <= selectedWorkout.getSets()) {
                                    if (selectedWorkout.getExerciseList().size() > 1) {
                                        nextExercise = selectedWorkout.getExerciseList().get((exerciseCount + 1) % (selectedWorkout.getExerciseList().size()));
                                    } else {
                                        if (setsDone <= selectedWorkout.getSets()) {
                                            nextExercise = selectedWorkout.getExerciseList().get(exerciseCount);
                                        } else {
                                            nextExercise = new Exercise();
                                        }
                                    }
                                } else {
                                    nextExercise = new Exercise();
                                }

                                updateExercises();
                            }
                        }
                    }

                    if (workoutButton.getText().equals("Start Workout")) {
                        exerciseCount = 0;
                        workoutButton.setText("Set");
                        currentExercise = selectedWorkout.getExerciseList().get(exerciseCount);
                        exerciseImage.setImageResource(imageResources.getImageResourceByName(getPictureNameForExercise(currentExercise.getName())));

                        if (selectedWorkout.getExerciseList().size() > 1) {
                            nextExercise = selectedWorkout.getExerciseList().get((exerciseCount + 1) % (selectedWorkout.getExerciseList().size()));
                        } else {
                            if (setsDone != (selectedWorkout.getSets() + 1)) {
                                nextExercise = selectedWorkout.getExerciseList().get(exerciseCount);
                            } else {
                                nextExercise = new Exercise();
                            }
                        }

                        updateExercises();
                        startExerciseTimerThread();

                        workoutTimerThread = new Thread() {
                            public void run() {
                                try {
                                    while (true) {
                                        workoutDuration++;
                                        sleep(1000);
                                    }
                                } catch (InterruptedException e) {

                                }
                            }
                        };
                        workoutTimerThread.start();

                    }

                }
            });
        }


        return root;
    }

    private void updateSetCount(){
        setsOutOfSets.setText(setsDone + " / " + selectedWorkout.getSets());
    }

    private void updateExercises(){
        currentExerciseName.setText(currentExercise.getName());

        String extraText = "";
        if(currentExercise.getDefaultWeightKG() != 0 || currentExercise.getDefaultWeightLBS() != 0){

            if(currentExercise.getDefaultWeightKG() != 0){
                extraText += "KG: " + currentExercise.getDefaultWeightKG();
            }

            if(currentExercise.getDefaultWeightLBS() != 0){
                extraText += "\tLBS: " + currentExercise.getDefaultWeightLBS();
            }
        }

        currentExerciseExtra.setText(extraText);

        if(currentExercise.getDefaultDistanceM() != 0){
            currentExerciseReps.setText(currentExercise.getDefaultDistanceM() + "m");
        }else{
            if(currentExercise.getDefaultReps() != 0) {
                currentExerciseReps.setText(currentExercise.getDefaultReps() + "");
            }else{
                currentExerciseReps.setText("");
            }
        }

        nextExerciseName.setText(nextExercise.getName());

        extraText = "";
        if(nextExercise.getDefaultWeightKG() != 0 || nextExercise.getDefaultWeightLBS() != 0){

            if(nextExercise.getDefaultWeightKG() != 0){
                extraText += "KG: " + nextExercise.getDefaultWeightKG();
            }

            if(nextExercise.getDefaultWeightLBS() != 0){
                extraText += "\tLBS: " + nextExercise.getDefaultWeightLBS();
            }
        }

        nextExerciseExtra.setText(extraText);

        if(nextExercise.getDefaultDistanceM() != 0){
            nextExerciseReps.setText(nextExercise.getDefaultDistanceM() + "m");
        }else{
            nextExerciseReps.setText(nextExercise.getDefaultReps() + "");
        }
    }

    private void startRestTimer(long time){
        workoutButton.setEnabled(false);
        stopExerciseTimerThread();
        CountDownTimer counter = new CountDownTimer(time * 1000, 1000){

            public void onTick(long millisUntilDone){
                int minutes = (int) millisUntilDone / 1000 / 60;
                int seconds = (int) millisUntilDone / 1000 % 60;

                String timerText = "! REST ! ";
                if(minutes < 10) timerText += "0";
                timerText += minutes + ":";

                if(seconds < 10) timerText += "0";
                timerText += seconds;
                timerText += " ! REST !";
                workoutTimer.setTextColor(Color.parseColor("#FF0000"));
                workoutTimer.setText(timerText);
            }

            public void onFinish() {
                workoutButton.setEnabled(true);
                workoutTimer.setText("00:00");
                workoutTimer.setTextColor(Color.parseColor("#FFFFFF"));
                startExerciseTimerThread();
            }
        }.start();
    }

    private void startExerciseTimerThread(){


        exerciseTimerThread = new Thread() {
            public void run() {
                try
                {
                    exerciseDuration = 0;
                    while(true) {
                        exerciseDuration++;
                        exerciseTimerString = "";

                        int minutes = exerciseDuration / 60;
                        int seconds = exerciseDuration % 60;

                        if(minutes < 10){
                            exerciseTimerString += "0";
                        }
                        exerciseTimerString += minutes + ":";

                        if(seconds < 10){
                            exerciseTimerString += "0";
                        }
                        exerciseTimerString += seconds;

                        //break out if fragment is closed
                        if(getActivity() == null)
                            return;

                        getActivity().runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                workoutTimer.setText(exerciseTimerString);
                            }

                        });
                        sleep(1000);

                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("Exercise Ended");
                }
            }
        };
        exerciseTimerThread.start();

    }

    private void stopExerciseTimerThread(){
        exerciseTimerThread.interrupt();
    }

    private String getPictureNameForExercise(String exerciseName){
        exerciseName = exerciseName.toLowerCase();
        exerciseName = exerciseName.replaceAll("\\s+","");
        exerciseName = exerciseName.replaceAll("-", "");
        System.out.println(exerciseName);
        return exerciseName;
    }

}
