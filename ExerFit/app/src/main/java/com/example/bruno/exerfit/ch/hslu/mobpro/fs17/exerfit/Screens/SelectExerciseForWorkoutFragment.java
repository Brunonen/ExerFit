package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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


public class SelectExerciseForWorkoutFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner categorySpinner;
    ListView exerciseListView;
    ArrayList<String> exerciseList = new ArrayList<String>();
    ArrayAdapter<String> exerciseAdapter;
    CreateWorkoutFragment parentFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_select_exercise_for_workout_fragment, container, false);

        categorySpinner = (Spinner) rootView.findViewById(R.id.spinnerCategorySelectExercise);
        categorySpinner.setOnItemSelectedListener(this);

        exerciseListView = (ListView) rootView.findViewById(R.id.selectExercisesForWorkoutList);


        exerciseAdapter=new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1,
                this.exerciseList);

        this.exerciseListView.setAdapter(this.exerciseAdapter);
        exerciseListView.setChoiceMode( AbsListView.CHOICE_MODE_SINGLE);
        final Button addExerciseToListButton = (Button) rootView.findViewById(R.id.addExercise);
        addExerciseToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //System.out.println(exerciseListView.getSelectedItem());
                parentFragment.addExerciseToWorkoutList("test");
            }
        });

        loadSpinnerDataCategory();

        return rootView;
    }

    private void loadSpinnerDataCategory(){
        final SQLWrapper sqlWrapper = new SQLWrapper(getActivity());

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

       // CreateWorkoutFragment activity = (CreateWorkoutFragment) getActivity();
        String selectedCategory = parentFragment.getCategoryFromSpinner();

        int position = labelsCategoriesString.indexOf(selectedCategory);
        categorySpinner.setSelection(position);

        getExercisesByCategoryFilter();
        /*categorySpinner.setEnabled(false);
        categorySpinner.setClickable(false);*/

        //getExercisesByCategoryFilter();
        //System.out.println(categorySpinner.getSelectedItem().toString());
    }

    private void getExercisesByCategoryFilter(){
        this.exerciseList.clear();

        String categorySelection = categorySpinner.getSelectedItem().toString();
        if(categorySelection.equals("-")) categorySelection = "";
        SQLWrapper sqlWrapper = new SQLWrapper(getActivity());
        List<Exercise> exerciseList = new ArrayList<>();
        if(!categorySelection.equals("")) {
            exerciseList = sqlWrapper.getExercisesByCategoryDescirption(categorySelection);
        }else{
            exerciseList = sqlWrapper.getAllExercises();
        }



        for(Exercise exec : exerciseList){
            this.exerciseList.add(exec.getName());
            this.exerciseAdapter.notifyDataSetChanged();
            //parentFragment.addExerciseToWorkoutList(exec.getName());
        }

        for(String s : this.exerciseList){
            System.out.println(s);
        }

    }

    public void setParentFragment(CreateWorkoutFragment parentFragment){
        this.parentFragment = parentFragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getExercisesByCategoryFilter();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
