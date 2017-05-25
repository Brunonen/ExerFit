package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.exerfit.R;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Category;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Exercise;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Location;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Type;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO.Workout;
import com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Database.SQLWrapper;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkoutFragment extends Fragment {

    Spinner categorySpinner;
    Spinner typeSpinner;
    Spinner locationSpinner;
    Spinner setsSpinner;
    Spinner restSetsSpinner;
    Spinner restExercisesSpinner;

    ListView exerciseListView;
    ArrayList<String> exerciseList = new ArrayList<String>();
    ArrayList<String> setSpinnerList = new ArrayList<>();
    ArrayList<String> restSetSpinnerList = new ArrayList<>();
    ArrayList<String> restExercisesSpinnerList = new ArrayList<>();
    ArrayAdapter<String> exerciseAdapter;
    CreateWorkoutFragment thisFragment;
    ArrayList<Exercise> exerciseListDTO = new ArrayList<>();
    ImageView intensityLevelImage;
    int intensityLevel = 0;
    SQLWrapper sqlWrapper;
    View rootView;
    TextView workoutName;
    ArrayList<Integer> intensityDrawables = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_create_workout_fragment, container, false);
        this.rootView = rootView;
        thisFragment = this;
        sqlWrapper = new SQLWrapper(getActivity());
        categorySpinner = (Spinner) rootView.findViewById(R.id.categorySpinner);
        typeSpinner = (Spinner) rootView.findViewById(R.id.typeSpinner);
        locationSpinner = (Spinner) rootView.findViewById(R.id.locationSpinner);
        setsSpinner = (Spinner) rootView.findViewById(R.id.spinnerSets);
        restSetsSpinner = (Spinner) rootView.findViewById(R.id.spinnerRestSets);
        restExercisesSpinner = (Spinner) rootView.findViewById(R.id.spinnerRestExercises);
        exerciseListView = (ListView) rootView.findViewById(R.id.exerciseListViewBox);
        intensityLevelImage = (ImageView) rootView.findViewById(R.id.imageViewIntensityLevek);

        loadSpinnerDataLocation();
        loadSpinnerDataType();
        loadSpinnerDataCategory();
        fillSetsSpinner();
        fillRestSetsSpinner();
        fillRestExercisesSpinner();

        intensityDrawables.add(R.drawable.intensitybar_0);
        intensityDrawables.add(R.drawable.intensitybar_1);
        intensityDrawables.add(R.drawable.intensitybar_2);
        intensityDrawables.add(R.drawable.intensitybar_3);
        intensityDrawables.add(R.drawable.intensitybar_4);
        intensityDrawables.add(R.drawable.intensitybar_5);
        intensityDrawables.add(R.drawable.intensitybar_6);
        intensityDrawables.add(R.drawable.intensitybar_7);
        intensityDrawables.add(R.drawable.intensitybar_8);
        intensityDrawables.add(R.drawable.intensitybar_9);
        intensityDrawables.add(R.drawable.intensitybar_10);

        intensityLevelImage.setImageResource(intensityDrawables.get(intensityLevel));

        exerciseAdapter=new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1,
                exerciseList);

        exerciseListView.setAdapter(exerciseAdapter);
        //workoutName = ((TextView) rootView.findViewById(R.id.editTextWorkoutName)).toString();

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

        workoutName = (TextView) rootView.findViewById(R.id.editTextWorkoutName);
        System.out.println(workoutName);
        final Button saveWorkout = (Button) rootView.findViewById(R.id.buttonSaveWorkout);
        saveWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                workoutName = (TextView) rootView.findViewById(R.id.editTextWorkoutName);
                if(workoutName.getText() != null && !workoutName.getText().toString().equals("")){
                    if(sqlWrapper.getWorkoutByNameWithExercises(workoutName.getText().toString()).getName() == null){
                        if(exerciseListDTO.size() > 0){
                            Type type = sqlWrapper.getTypeByDescirption(typeSpinner.getSelectedItem().toString());
                            Location location = sqlWrapper.getLocationByDescription(locationSpinner.getSelectedItem().toString());
                            Workout workout = new Workout(0, workoutName.getText().toString(), type, location, exerciseListDTO,
                                    Integer.parseInt(setsSpinner.getSelectedItem().toString()),
                                    Integer.parseInt(restSetsSpinner.getSelectedItem().toString()),
                                    Integer.parseInt(restExercisesSpinner.getSelectedItem().toString()));
                            int workoutID = sqlWrapper.addWorkout(workout);

                            if(sqlWrapper.getWorkoutByIDNoExercises(workoutID).getName().equals(workout.getName())) {
                                MainActivity activity = (MainActivity) getActivity();
                                activity.setSelectedWorkout(workout.getName());

                                Fragment fragment;
                                fragment = new PreWorkoutFragment();

                                activity.changeFragment(fragment);
                            }else{
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT);
                                System.out.println("Something went wrong");
                            }
                        }else{
                            Toast.makeText(getActivity(), "Workout can't have no exercises!", Toast.LENGTH_SHORT);
                            System.out.println("Workout can't have no exercises!");
                        }
                    }else{
                        Toast.makeText(getActivity(), "A workout with that Name already exists!", Toast.LENGTH_SHORT);
                        System.out.println("A workout with that Name already exists!");
                    }
                }else{
                    Toast.makeText(getActivity(), "Please enter a unique workout Name", Toast.LENGTH_SHORT);
                    System.out.println("Please enter a unique workout Name");
                }
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

    private void fillSetsSpinner(){
        for(int count = 1; count <= 7; count++){
            setSpinnerList.add("" + count);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, setSpinnerList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        setsSpinner.setAdapter(dataAdapter);
    }

    private void fillRestSetsSpinner(){
        for(int count = 20; count <= 180; count+=20){
            restSetSpinnerList.add("" + count);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, restSetSpinnerList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        restSetsSpinner.setAdapter(dataAdapter);
    }

    private void fillRestExercisesSpinner(){
        for(int count = 0; count <= 60; count+=5){
            restExercisesSpinnerList.add("" + count);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, restExercisesSpinnerList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        restExercisesSpinner.setAdapter(dataAdapter);
    }

    public void addExerciseToWorkoutList(Exercise exerciseToAdd){
        this.exerciseList.add(exerciseToAdd.getName());
        this.exerciseListDTO.add(exerciseToAdd);
        this.exerciseAdapter.notifyDataSetChanged();

        int intensityLevel = 0;
        for(Exercise exec : exerciseListDTO){
            System.out.println(exec.getName());
            intensityLevel += exec.getIntensity();
        }

        this.intensityLevel = intensityLevel / exerciseListDTO.size();
        //this.intensityLevelImage.setImageResource(this.intensityDrawables.get(intensityLevel));

    }

}
