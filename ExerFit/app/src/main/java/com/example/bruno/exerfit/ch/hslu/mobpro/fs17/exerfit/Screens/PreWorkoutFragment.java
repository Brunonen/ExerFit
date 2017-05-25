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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.ArrayList;
import java.util.List;


public class PreWorkoutFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pre_workout, container, false);
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

            List<String> exersiseString = new ArrayList<>();

            for (Exercise exercise : excersiseList) {
                exersiseString.add(exercise.getName());
            }

            TextView setsInfo = (TextView) root.findViewById(R.id.textViewSets);
            setsInfo.setText("" + workout.getSets());

            TextView restBetweenSetsInfo = (TextView) root.findViewById(R.id.textRestBetweenSets);
            restBetweenSetsInfo.setText("" + workout.getRestBetweenSets());

            TextView restBetweenExercisesInfo = (TextView) root.findViewById(R.id.textRestBetweenExercises);
            restBetweenExercisesInfo.setText("" + workout.getRestBetweenExercises());

            final ListView listView = (ListView) root.findViewById(R.id.exerciseList);
            ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, exersiseString);

            listView.setAdapter(myarrayAdapter);
        }else{
            Toast.makeText(this.getActivity() ,"No Workout Selected",  Toast.LENGTH_SHORT).show();
        }

        final ListView mExerciseList = (ListView) root.findViewById(R.id.exerciseList);
        mExerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedExercise = mExerciseList.getItemAtPosition(position).toString();
                Fragment fragment;
                fragment = new ExerciseInfoFragment();
                MainActivity activity = (MainActivity) getActivity();
                activity.setSelectedExercise(selectedExercise);
                activity.changeFragment(fragment);
            }
        });
    }
}