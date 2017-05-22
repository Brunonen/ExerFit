package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Category;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Location;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Type;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkoutFragment extends Fragment {

    Spinner categorySpinner;
    Spinner typeSpinner;
    Spinner locationSpinner;
    ListView exerciseListView;
    ArrayList<String> exerciseList = new ArrayList<String>();
    ArrayAdapter<String> exerciseAdapter;
    CreateWorkoutFragment thisFragment;
    ArrayList<Exercise> exerciseListDTO = new ArrayList<>();
    SQLWrapper sqlWrapper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_create_workout_fragment, container, false);
        thisFragment = this;
        sqlWrapper = new SQLWrapper(getActivity());
        categorySpinner = (Spinner) rootView.findViewById(R.id.categorySpinner);
        typeSpinner = (Spinner) rootView.findViewById(R.id.typeSpinner);
        locationSpinner = (Spinner) rootView.findViewById(R.id.locationSpinner);
        exerciseListView = (ListView) rootView.findViewById(R.id.exerciseListViewBox);

        loadSpinnerDataLocation();
        loadSpinnerDataType();
        loadSpinnerDataCategory();

        exerciseAdapter=new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1,
                exerciseList);

        exerciseListView.setAdapter(exerciseAdapter);

        final Button addExerciseToListButton = (Button) rootView.findViewById(R.id.addExerciseToWorkoutButton);
        addExerciseToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                SelectExerciseForWorkoutFragment fragment;
                fragment = new SelectExerciseForWorkoutFragment();
                fragment.setParentFragment(thisFragment);

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack("LastFragment").commit();
            }
        });

        return rootView;
    }

    private void loadSpinnerDataLocation(){
        // Spinner Drop down elements
        List<Location> locations = sqlWrapper.getAllLocations();

        List<String> labelsString= new ArrayList<>();

        for(Location location : locations ){
            labelsString.add(location.getDescription());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, labelsString);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        locationSpinner.setAdapter(dataAdapter);
    }

    private void loadSpinnerDataCategory(){

        // Spinner Drop down elements
        List<Category> categories = sqlWrapper.getAllCategories();

        List<String> labelsCategoriesString = new ArrayList<>();
        labelsCategoriesString.add("-");
        for(Category cat : categories ){
            labelsCategoriesString.add(cat.getDescription());
        }

        // Creating adapter for spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, labelsCategoriesString);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        categorySpinner.setAdapter(dataAdapter);
        categorySpinner.setEnabled(false);
        categorySpinner.setClickable(false);

    }

    private void loadSpinnerDataType(){
        // Spinner Drop down elements
        List<Type> types = sqlWrapper.getAllTypes();

        List<String> labelsTypesString = new ArrayList<>();
        for(Type type : types ){
            labelsTypesString.add(type.getDescription());
        }

        // Creating adapter for spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, labelsTypesString);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        typeSpinner.setAdapter(dataAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int pos, long arg3) {
                String Text = parent.getSelectedItem().toString();
                if (Text.equals("Single Muscle")) {
                    categorySpinner.setEnabled(true);
                    categorySpinner.setClickable(true);
                }else{
                    categorySpinner.setEnabled(false);
                    categorySpinner.setClickable(false);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }

    public String getCategoryFromSpinner(){
        return this.categorySpinner.getSelectedItem().toString();
    }

    public void addExerciseToWorkoutList(String exerciseName){

        this.exerciseList.add(exerciseName);
        this.exerciseListDTO.add(sqlWrapper.getExerciseByID(1));
        this.exerciseAdapter.notifyDataSetChanged();


    }

}
