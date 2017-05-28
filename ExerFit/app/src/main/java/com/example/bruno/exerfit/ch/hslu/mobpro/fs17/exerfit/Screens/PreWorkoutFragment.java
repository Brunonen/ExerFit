package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Adapters.CustomAdapterExercises;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.ArrayList;
import java.util.List;


public class PreWorkoutFragment extends Fragment {

    ArrayList<Integer> intensityDrawables = new ArrayList<>();
    ImageView intensityLevelImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pre_workout, container, false);

        intensityDrawables.add(R.drawable.intensitybar0);
        intensityDrawables.add(R.drawable.intensitybar1);
        intensityDrawables.add(R.drawable.intensitybar2);
        intensityDrawables.add(R.drawable.intensitybar3);
        intensityDrawables.add(R.drawable.intensitybar4);
        intensityDrawables.add(R.drawable.intensitybar5);
        intensityDrawables.add(R.drawable.intensitybar6);
        intensityDrawables.add(R.drawable.intensitybar7);
        intensityDrawables.add(R.drawable.intensitybar8);
        intensityDrawables.add(R.drawable.intensitybar9);
        intensityDrawables.add(R.drawable.intensitybar10);

        intensityLevelImage = (ImageView) root.findViewById(R.id.imageViewIntensityLeveLWorkout);

        intensityLevelImage.setImageResource(intensityDrawables.get(0));

        loadWorkout(root);
        return root;
    }


    public void loadWorkout(View root){
        MainActivity activity = (MainActivity) getActivity();
        String workoutName = activity.getSelectedWorkout();

        if (workoutName != null) {
            TextView workoutTitle = (TextView) root.findViewById(R.id.workoutName);
            workoutTitle.setText(workoutName);

            SQLWrapper db = new SQLWrapper(getActivity());
            Workout workout = db.getWorkoutByNameWithExercises(workoutName);
            List<Exercise> excersiseList = workout.getExerciseList();

            int intensityLevelMed = 0;
            for(Exercise exec : excersiseList){
                intensityLevelMed += exec.getIntensity();
            }

            intensityLevelMed = intensityLevelMed / excersiseList.size();

            intensityLevelImage.setImageResource(intensityDrawables.get(intensityLevelMed));

            TextView setsInfo = (TextView) root.findViewById(R.id.textViewSets);
            setsInfo.setText("" + workout.getSets());

            TextView restBetweenSetsInfo = (TextView) root.findViewById(R.id.textRestBetweenSets);
            restBetweenSetsInfo.setText("" + workout.getRestBetweenSets());

            TextView restBetweenExercisesInfo = (TextView) root.findViewById(R.id.textRestBetweenExercises);
            restBetweenExercisesInfo.setText("" + workout.getRestBetweenExercises());

            ListView listView = (ListView) root.findViewById(R.id.exerciseList);

            CustomAdapterExercises adapter = new CustomAdapterExercises (activity, excersiseList);

            listView.setAdapter(adapter);

        }else{
            Toast.makeText(this.getActivity() ,"No Workout Selected",  Toast.LENGTH_SHORT).show();
        }

        final ListView mExerciseList = (ListView) root.findViewById(R.id.exerciseList);
        mExerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise selectedExercise = (Exercise)mExerciseList.getItemAtPosition(position);
                Fragment fragment;
                fragment = new ExerciseInfoFragment();
                MainActivity activity = (MainActivity) getActivity();
                activity.setSelectedExercise(selectedExercise.getName());
                activity.changeFragment(fragment);
            }
        });

        final Button addExerciseToListButton = (Button) root.findViewById(R.id.buttonStartWorkout);
        addExerciseToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MainActivity activity = (MainActivity) getActivity();
                Fragment fragment = new WorkoutFragment();
                activity.changeFragment(fragment);
            }
        });
    }
}