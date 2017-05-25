package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;

import java.util.List;

/**
 * Created by bruno on 25/05/2017.
 */

public class CustomAdapterExercises extends ArrayAdapter<Exercise> {
    private final Activity context;
    private final List<Exercise> items;

    public CustomAdapterExercises(Activity context, List<Exercise> items) {

        super(context, R.layout.exercise_info_layout, items);
        this.context = context;
        this.items= items;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.exercise_info_layout, null, true);

        TextView execName = (TextView) rowView.findViewById(R.id.listExerciseInfoName);
        TextView execReps = (TextView) rowView.findViewById(R.id.listExerciseInfoReps);
        TextView execIntensity = (TextView) rowView.findViewById(R.id.listExerciseInfoIntensity);
        Exercise tmp = items.get(position);

        execName.setText(tmp.getName());
        execReps.setText("" + tmp.getDefaultReps());
        execIntensity.setText("" + tmp.getIntensity());

        return rowView;
    }
}
