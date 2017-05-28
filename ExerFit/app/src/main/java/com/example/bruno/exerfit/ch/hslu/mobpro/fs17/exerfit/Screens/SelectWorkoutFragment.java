package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.ArrayList;
import java.util.List;

public class SelectWorkoutFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.activity_select_workout_fragment, container, false);
        showWorkouts(root);
        return root;

    }

    public void showWorkouts(View root) {
        final MainActivity activity = (MainActivity) getActivity();
        String location;
        String category;
        category = activity.getSelectedType();
        location = activity.getSelectedLocation();

        SQLWrapper db = new SQLWrapper(getActivity());
        List<Workout> workoutList = db.getWorkoutByFilterString(category, location);
        List<String> workoutString = new ArrayList<>();

        for (Workout workout : workoutList) {
            workoutString.add(workout.getName());
            System.out.println(workout.getName());
        }


        final ListView listView = (ListView) root.findViewById(R.id.listWorkouts);
        ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this.getActivity(),  R.layout.listitem_white_text, workoutString);

        listView.setAdapter(myarrayAdapter);

        final ListView mWorkoutList = (ListView) root.findViewById(R.id.listWorkouts);
        mWorkoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedWorkout = mWorkoutList.getItemAtPosition(position).toString();
                Fragment fragment;
                fragment = new PreWorkoutFragment();
                MainActivity activity = (MainActivity) getActivity();
                activity.setSelectedWorkout(selectedWorkout);
                activity.changeFragment(fragment);
            }
        });

    }

}
