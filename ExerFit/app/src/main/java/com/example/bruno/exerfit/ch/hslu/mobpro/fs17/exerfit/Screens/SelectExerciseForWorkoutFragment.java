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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class SelectExerciseForWorkoutFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner categorySpinner;
    ListView exerciseListView;
    ArrayList<String> exerciseList = new ArrayList<String>();
    ArrayList<String> repetitionsList = new ArrayList<>();
    ArrayList<String> distanceList = new ArrayList<>();
    ArrayList<String> weightListKG = new ArrayList<>();
    ArrayList<String> weightListLBS =  new ArrayList<>();
    ArrayAdapter<String> exerciseAdapter;
    CreateWorkoutFragment parentFragment;
    String selectedExercise;
    Spinner repetitionsSpinner;
    Spinner distanceSpinner;
    Spinner weightSpinnerKG;
    Spinner weightSpinnerLBS;
    SQLWrapper sqlWrapper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_select_exercise_for_workout_fragment, container, false);

        sqlWrapper = new SQLWrapper(getActivity());
        categorySpinner = (Spinner) rootView.findViewById(R.id.spinnerCategorySelectExercise);
        categorySpinner.setOnItemSelectedListener(this);

        repetitionsSpinner = (Spinner) rootView.findViewById(R.id.spinnerReps);
        distanceSpinner = (Spinner) rootView.findViewById(R.id.spinnerDistance);
        weightSpinnerKG = (Spinner) rootView.findViewById(R.id.spinnerWeightKG);
        weightSpinnerLBS = (Spinner) rootView.findViewById(R.id.spinnerWeightLBS);

        exerciseListView = (ListView) rootView.findViewById(R.id.selectExercisesForWorkoutList);


        exerciseAdapter=new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1,
                this.exerciseList);

        this.exerciseListView.setAdapter(this.exerciseAdapter);

        exerciseListView.setChoiceMode( AbsListView.CHOICE_MODE_SINGLE);
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedExercise = exerciseListView.getItemAtPosition(position).toString();
                Exercise exerciseSelected = sqlWrapper.getExerciseByName(selectedExercise);
                System.out.println(exerciseSelected.getName() + "\nReps: " + exerciseSelected.getDefaultReps() +
                "\nDistance: " + exerciseSelected.getDefaultDistanceM() + "\nKG: " + exerciseSelected.getDefaultWeightKG() +
                "\nLBS: " + exerciseSelected.getDefaultWeightLBS());
                int repPosition = repetitionsList.indexOf("" + exerciseSelected.getDefaultReps());
                int distancePosition = distanceList.indexOf("" + exerciseSelected.getDefaultDistanceM());
                int weightPositionKG = weightListKG.indexOf("" + exerciseSelected.getDefaultWeightKG());
                int weightPositionLBS = weightListLBS.indexOf("" + exerciseSelected.getDefaultWeightLBS());


                repetitionsSpinner.setSelection(repPosition);
                distanceSpinner.setSelection(distancePosition);
                weightSpinnerKG.setSelection(weightPositionKG);
                weightSpinnerLBS.setSelection(weightPositionLBS);
            }
        });

        final Button addExerciseToListButton = (Button) rootView.findViewById(R.id.addExercise);
        addExerciseToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!selectedExercise.equals("")) {
                    Exercise exerciseToAdd = sqlWrapper.getExerciseByName(selectedExercise);

                    exerciseToAdd.setDefaultReps(Integer.parseInt(repetitionsSpinner.getSelectedItem().toString()));
                    exerciseToAdd.setDefaultDistanceM(Integer.parseInt(distanceSpinner.getSelectedItem().toString()));
                    exerciseToAdd.setDefaultWeightKG(Integer.parseInt(weightSpinnerKG.getSelectedItem().toString()));
                    exerciseToAdd.setDefaultWeightLBS(Integer.parseInt(weightSpinnerLBS.getSelectedItem().toString()));

                    parentFragment.addExerciseToWorkoutList(exerciseToAdd);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.popBackStackImmediate();
                }
            }
        });

        loadSpinnerDataCategory();
        fillRepetitionsList();
        fillDistanceList();
        fillWeightListKG();
        fillWeightListLBS();

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

        if(exerciseList.size() > 0) {
            for (Exercise exec : exerciseList) {
                this.exerciseList.add(exec.getName());
            }
        }
        this.exerciseAdapter.notifyDataSetChanged();
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

    private void fillRepetitionsList(){
        for(int count = 0; count <= 40; count++){
            repetitionsList.add("" + count);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, repetitionsList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        repetitionsSpinner.setAdapter(dataAdapter);
    }

    private void fillDistanceList(){
        for(int count = 0; count <= 15000; count+=500){
            distanceList.add("" + count);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, distanceList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        distanceSpinner.setAdapter(dataAdapter);
    }

    private void fillWeightListKG(){
        for(int count = 0; count <= 120; count+=5){
            weightListKG.add("" + count);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, weightListKG);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        weightSpinnerKG.setAdapter(dataAdapter);
    }

    private void fillWeightListLBS(){
        for(int count = 0; count <= 240; count+=10){
            weightListLBS.add("" + count);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, weightListLBS);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        weightSpinnerLBS.setAdapter(dataAdapter);
    }
}
