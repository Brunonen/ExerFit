package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

public class ExerciseInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_exercise_info_fragment, container, false);
        showExerciseInfo(root);
        return root;
    }

    public void showExerciseInfo(View root){

        MainActivity activity = (MainActivity) getActivity();
        String exerciseName = activity.getSelectedExercise();

        if (exerciseName != null) {
            SQLWrapper db = new SQLWrapper(getActivity());
            Exercise exercise = db.getExerciseByName(exerciseName);

            TextView exerciseTitle = (TextView) root.findViewById(R.id.exerciseName);
            exerciseTitle.setText(exerciseName);

            TextView exerciseDesc = (TextView) root.findViewById(R.id.exerciseDescription);
            exerciseDesc.setText(exercise.getDescription());

            TextView exerciseLoc = (TextView) root.findViewById(R.id.exerciseLocation);
            exerciseLoc.setText(exercise.getLocation().getDescription());

            TextView exerciseCat = (TextView) root.findViewById(R.id.exerciseCategory);
            exerciseCat.setText(exercise.getCategory().getDescription());

            TextView exerciseRep = (TextView) root.findViewById(R.id.exerciseReps);
            exerciseRep.setText(String.valueOf(exercise.getDefaultReps()));

            TextView exerciseInten = (TextView) root.findViewById(R.id.exerciseIntensitiy);
            exerciseInten.setText(String.valueOf(exercise.getIntensity()));

            TextView exerciseKg = (TextView) root.findViewById(R.id.exerciseWeightKg);
            exerciseKg.setText(String.valueOf(exercise.getDefaultWeightKG()));

            TextView exerciseLbs = (TextView) root.findViewById(R.id.exerciseLbs);
            exerciseLbs.setText(String.valueOf(exercise.getDefaultWeightLBS()));

            TextView exerciseDist = (TextView) root.findViewById(R.id.exerciseDistance);
            exerciseDist.setText(String.valueOf(exercise.getDefaultDistanceM()));


        }
    }
}
