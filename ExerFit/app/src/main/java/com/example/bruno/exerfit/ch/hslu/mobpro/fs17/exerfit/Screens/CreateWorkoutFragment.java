package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bruno.exerfit.R;

public class CreateWorkoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_create_workout_fragment, container, false);
    }
}
