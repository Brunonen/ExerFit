package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

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

    public void showWorkouts(View root){
        MainActivity activity = (MainActivity) getActivity();
        String location;
        String category;
        category = activity.getSelectedCategory();
        location = activity.getSelectedLocation();

        SQLWrapper db = new SQLWrapper(getActivity());
        List<Workout> workoutList = db.getWorkoutByFilterString(category, location);
        List<String> workoutString = new ArrayList<>();

        for (Workout workout : workoutList){
            workoutString.add(workout.getName());
            System.out.println(workout.getName());
        }


        ListView listView = (ListView) root.findViewById(R.id.listWorkouts);
        ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, workoutString);

        listView.setAdapter(myarrayAdapter);

    }

}
