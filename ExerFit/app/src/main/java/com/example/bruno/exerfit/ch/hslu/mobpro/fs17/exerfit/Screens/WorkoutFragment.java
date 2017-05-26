package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Adapters.CustomAdapterExercises;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.List;

public class WorkoutFragment extends Fragment {

    Workout selectedWorkout;
    SQLWrapper sqlWrapper;
    String selectedWorkoutString;
    TextView setsOutOfSets;
    int setsDone = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_workout_fragment, container, false);

        sqlWrapper = new SQLWrapper(getActivity());

        MainActivity activity = (MainActivity) getActivity();
        selectedWorkoutString = activity.getSelectedWorkout();
        selectedWorkout = sqlWrapper.getWorkoutByNameWithExercises(selectedWorkoutString);

        List<Exercise> exercises = selectedWorkout.getExerciseList();
        System.out.print(exercises.size());
        CustomAdapterExercises adapter = new CustomAdapterExercises (activity, exercises);

        ListView listView = (ListView) root.findViewById(R.id.listViewDoWorkoutExercises);
        listView.setAdapter(adapter);

        setsOutOfSets = (TextView) root.findViewById(R.id.textViewSetsOutOfSets);
        updateSetCount();

        final Button addExerciseToListButton = (Button) root.findViewById(R.id.setAndWorkoutButton);
        addExerciseToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setsDone++;
                updateSetCount();
            }
        });

        return root;
    }

    private void updateSetCount(){
        setsOutOfSets.setText(setsDone + " / " + selectedWorkout.getSets());
    }
}
