package com.example.bruno.exerfit;

/**
 * Created by bruno on 01/05/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLWrapper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "exerfit";
    // Table Names
    private static final String TABLE_EXERCISES = "tbExercises";
    private static final String TABLE_WORKOUTS = "tbWorkouts";
    private static final String TABLE_WORKOUT_X_EXERCISES = "tbWorkoutXExercises";
    private static final String TABLE_SCHEDULE = "tbSchedule";
    private static final String TABLE_CATEGORY = "tbCategory";
    private static final String TABLE_LOCATION = "tbLocation";
    private static final String TABLE_TYPE = "tbType";

    //Category Table Columns Names
    private static final String CATEGORY_ID = "categoryID";
    private static final String CATEGORY_DESCRIPTION = "Description";

    //Location Table Columns names
    private static final String LOCATION_ID = "locationID";
    private static final String LOCATION_DESCRIPTION = "Description";

    //Type Table Columns names
    private static final String TYPE_ID = "typeID";
    private static final String TYPE_DESCRIPTION = "Description";

    // Exercises Table Columns names
    private static final String EXERCISE_ID = "exerciseID";
    private static final String EXERCISE_NAME = "Name";
    private static final String EXERCISE_DESCRIPTION = "Description";
    private static final String EXERCISE_INTENSITY = "Intensity";
    private static final String EXERCISE_LOCATION = "Location";
    private static final String EXERCISE_CATEGORY = "Category";
    private static final String EXERCISE_DEFAULT_REPS = "DefaultReps";
    private static final String EXERCISE_DEFAULT_WEIGHT_KG = "DefaultWeightKG";
    private static final String EXERCISE_DEFAULT_WEIGHT_LBS = "DefaultWeightLBS";
    private static final String EXERCISE_DEFAULT_DISTANCE_M = "DefaultDistanceM";

    //Workouts Table Columns Names
    private static final String WORKOUTS_ID = "workoutID";
    private static final String WORKOUTS_NAME = "Name";
    private static final String WORKOUTS_Type = "Type";
    private static final String WORKOUTS_LOCATION = "Location";
    private static final String WORKOUTS_SETS = "Sets";
    private static final String WORKOUTS_REST_BETWEEN_SETS = "RestBetweenSets";
    private static final String WORKOUTS_REST_BETWEEN_EXERCISES = "RestBetweenExercises";

    //Workouts X Exercises Table Column Names
    private static final String W_X_E_WORKOUT_ID = "workout_ID";
    private static final String W_X_E_EXERCISE_ID = "exercise_ID";
    private static final String W_X_E_CUSTOM_REPS = "CustomReps";
    private static final String W_X_E_CUSTOM_WEIGHT_KG = "CustomWeightKG";
    private static final String W_X_E_CUSTOM_WEIGHT_LBS = "CustomWeightLBS";
    private static final String W_X_E_CUSTOM_DISTANCE_M = "CustomDistanceM";

    //SChedule Table Column Names
    private static final String SCHEDULE_WORKOUT_ID = "WorkoutID";
    private static final String SCHEDULE_DAY_OF_THE_WEEK = "DayOfTheWeek";

    public SQLWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS '" + TABLE_CATEGORY + "'("
                + CATEGORY_ID + " INTEGER PRIMARY KEY," + CATEGORY_DESCRIPTION + " varchar(45) NOT NULL UNIQUE)";

        String CREATE_LOCATION_TABLE = "CREATE TABLE IF NOT EXISTS '" + TABLE_LOCATION + "'("
                + LOCATION_ID + " INTEGER PRIMARY KEY," + LOCATION_DESCRIPTION + " varchar(45) NOT NULL UNIQUE)";

        String CREATE_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS '" + TABLE_TYPE + "'("
                + TYPE_ID + " INTEGER PRIMARY KEY," + TYPE_DESCRIPTION + " varchar(45) NOT NULL UNIQUE)";

        String CREATE_TABLE_EXERCISES = "CREATE TABLE IF NOT EXISTS '" + TABLE_EXERCISES +"' ("
                +"'"+ EXERCISE_ID +"' INTEGER PRIMARY KEY,"
                +"'"+ EXERCISE_NAME +"' varchar(45) NOT NULL UNIQUE,"
                +"'"+ EXERCISE_DESCRIPTION +"' longtext NOT NULL,"
                +"'"+ EXERCISE_INTENSITY +"' int(11) NOT NULL,"
                +"'"+ EXERCISE_LOCATION +"' int(11) NOT NULL,"
                +"'"+ EXERCISE_CATEGORY +"' int(11) NOT NULL,"
                +"'"+ EXERCISE_DEFAULT_REPS +"' int(11) DEFAULT NULL,"
                +"'"+ EXERCISE_DEFAULT_WEIGHT_KG +"' int(11) DEFAULT NULL,"
                +"'"+ EXERCISE_DEFAULT_WEIGHT_LBS +"' int(11) DEFAULT NULL,"
                +"'"+ EXERCISE_DEFAULT_DISTANCE_M +"' int(11) DEFAULT NULL,"
                +"FOREIGN KEY (`"+EXERCISE_CATEGORY+"`) REFERENCES `"+TABLE_CATEGORY+"` (`"+CATEGORY_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                +"FOREIGN KEY (`"+EXERCISE_LOCATION+"`) REFERENCES `"+TABLE_LOCATION+"` (`"+LOCATION_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION"
                +")";

        String CREATE_TABLE_WORKOUTS = "CREATE TABLE IF NOT EXISTS `"+TABLE_WORKOUTS+"` ("
                +"`"+WORKOUTS_ID+"` INTEGER PRIMARY KEY,"
                +"`"+WORKOUTS_NAME+"` varchar(45) NOT NULL,"
                +"`"+WORKOUTS_Type+"` int(11) NOT NULL,"
                +"`"+WORKOUTS_LOCATION+"` int(11) NOT NULL,"
                +"`"+WORKOUTS_SETS+"` int(11) DEFAULT NULL,"
                +"`"+WORKOUTS_REST_BETWEEN_SETS+"` int(11) NOT NULL,"
                +"`"+WORKOUTS_REST_BETWEEN_EXERCISES+"` int(11) NOT NULL,"
                +"FOREIGN KEY (`"+WORKOUTS_Type+"`) REFERENCES `"+TABLE_TYPE+"` (`"+TYPE_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                +"FOREIGN KEY (`"+WORKOUTS_LOCATION+"`) REFERENCES `"+TABLE_LOCATION+"` (`"+LOCATION_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION"
                +")";

        String CREATE_TABLE_WORKOUTS_X_EXERCISES = "CREATE TABLE IF NOT EXISTS `"+TABLE_WORKOUT_X_EXERCISES+"` ("
                +"`"+W_X_E_WORKOUT_ID+"` int(11) NOT NULL,"
                +"`"+W_X_E_EXERCISE_ID+"` int(11) NOT NULL,"
                +"`"+W_X_E_CUSTOM_REPS+"` int(11) DEFAULT NULL,"
                +"`"+W_X_E_CUSTOM_WEIGHT_KG+"` int(11) DEFAULT NULL,"
                +"`"+W_X_E_CUSTOM_WEIGHT_LBS+"` int(11) DEFAULT NULL,"
                +"`"+W_X_E_CUSTOM_DISTANCE_M+"` int(11) DEFAULT NULL,"
                +"FOREIGN KEY (`"+W_X_E_EXERCISE_ID+"`) REFERENCES `"+TABLE_EXERCISES+"` (`"+EXERCISE_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                +"FOREIGN KEY (`"+W_X_E_WORKOUT_ID+"`) REFERENCES `"+TABLE_WORKOUTS+"` (`"+WORKOUTS_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION"
                +")";

        String CREATE_TABLE_SCHEDULE = "CREATE TABLE IF NOT EXISTS`"+TABLE_SCHEDULE+"` (" +
                "  `"+SCHEDULE_WORKOUT_ID+"` int(11) NOT NULL," +
                "  `"+SCHEDULE_DAY_OF_THE_WEEK+"` int(11) NOT NULL," +
                " FOREIGN KEY (`"+SCHEDULE_WORKOUT_ID+"`) REFERENCES `"+TABLE_WORKOUTS+"` (`"+WORKOUTS_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ")";

        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_LOCATION_TABLE);
        db.execSQL(CREATE_TYPE_TABLE);
        db.execSQL(CREATE_TABLE_EXERCISES);
        db.execSQL(CREATE_TABLE_WORKOUTS);
        db.execSQL(CREATE_TABLE_WORKOUTS_X_EXERCISES);
        db.execSQL(CREATE_TABLE_SCHEDULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPS);
        // Creating tables again
        //onCreate(db);
    }

    public void addCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_DESCRIPTION, category.getDescription());
        db.insert(TABLE_CATEGORY, null, values);
        db.close();
    }

    public void addLocation(Location location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOCATION_DESCRIPTION, location.getDescription());
        db.insert(TABLE_LOCATION, null, values);
        db.close();
    }

    public void addType(Type type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TYPE_DESCRIPTION, type.getDescription());
        db.insert(TABLE_TYPE, null, values);
        db.close();
    }

    public void addExercise(Exercise exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXERCISE_NAME, exercise.getName());
        values.put(EXERCISE_DESCRIPTION, exercise.getDescription());
        values.put(EXERCISE_INTENSITY, exercise.getIntensity());
        values.put(EXERCISE_CATEGORY, exercise.getCategory().getCategoryID());
        values.put(EXERCISE_LOCATION, exercise.getLocation().getLocationID());
        values.put(EXERCISE_DEFAULT_REPS, exercise.getDefaultReps());
        values.put(EXERCISE_DEFAULT_WEIGHT_KG, exercise.getDefaultWeightKG());
        values.put(EXERCISE_DEFAULT_WEIGHT_LBS, exercise.getDefaultWeightLBS());
        values.put(EXERCISE_DEFAULT_DISTANCE_M, exercise.getDefaultDistanceM());
        db.insert(TABLE_EXERCISES, null, values);

        db.close();
    }

    public void addWorkout(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORKOUTS_NAME, workout.getName());
        values.put(WORKOUTS_Type, workout.getType().getTypeID());
        values.put(WORKOUTS_LOCATION, workout.getLocation().getLocationID());
        values.put(WORKOUTS_SETS, workout.getSets());
        values.put(WORKOUTS_REST_BETWEEN_SETS, workout.getRestBetweenSets());
        values.put(WORKOUTS_REST_BETWEEN_EXERCISES, workout.getRestBetweenExercises());
        db.insert(TABLE_WORKOUTS, null, values);

        db.close();
    }

    public void addWorkoutXExercise(Workout_X_Exercise wkXe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(W_X_E_WORKOUT_ID, wkXe.getWorkout().getWorkoutID());
        values.put(W_X_E_EXERCISE_ID, wkXe.getExercise().getExerciseID());
        values.put(W_X_E_CUSTOM_REPS, wkXe.getCustomReps());
        values.put(W_X_E_CUSTOM_WEIGHT_KG, wkXe.getCustomWeightKG());
        values.put(W_X_E_CUSTOM_WEIGHT_LBS, wkXe.getCustomWeightLBS());
        values.put(W_X_E_CUSTOM_DISTANCE_M, wkXe.getCustomDistanceM());
        db.insert(TABLE_WORKOUT_X_EXERCISES, null, values);

        db.close();
    }

    public void addSchedule(Schedule schedule){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SCHEDULE_WORKOUT_ID, schedule.getWorkout().getWorkoutID());
        values.put(SCHEDULE_DAY_OF_THE_WEEK, schedule.getDayOfTheWeek());
        db.insert(TABLE_SCHEDULE, null, values);

        db.close();
    }

    public List<Category> getAllCategories(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Category> categoryList = new ArrayList<Category>();
        String sqlSelect = "SELECT * FROM " + TABLE_CATEGORY;
        Cursor cursor = db.rawQuery(sqlSelect, null);
        if(cursor.moveToFirst()){
            do{
                categoryList.add(new Category(Integer.parseInt(cursor.getString(0)), cursor.getString(1)));
            }while(cursor.moveToNext());
        }

        return categoryList;
    }

    public Category getCategoryByID(int categoryID){
        SQLiteDatabase db = this.getReadableDatabase();
        Category category = new Category();
        String sqlSelect = "SELECT * FROM " + TABLE_CATEGORY + " WHERE " + CATEGORY_ID + " = " + categoryID;
        Cursor cursor = db.rawQuery(sqlSelect, null);
        if(cursor.moveToFirst()){

                return new Category(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }
        return category;
    }

    public Category getCategoryByDescription(String categoryDescription){
        SQLiteDatabase db = this.getReadableDatabase();
        Category category = new Category();
        String sqlSelect = "SELECT * FROM " + TABLE_CATEGORY + " WHERE " + CATEGORY_DESCRIPTION + " = " + categoryDescription;
        Cursor cursor = db.rawQuery(sqlSelect, null);
        if(cursor.moveToFirst()){

            return new Category(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }
        return category;
    }

    public List<Location> getAllLocations(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Location> locationList = new ArrayList<Location>();
        String sqlSelect = "SELECT * FROM " + TABLE_LOCATION;
        Cursor cursor = db.rawQuery(sqlSelect, null);
        if(cursor.moveToFirst()){
            do{
                locationList.add(new Location(Integer.parseInt(cursor.getString(0)), cursor.getString(1)));
            }while(cursor.moveToNext());
        }

        return locationList;
    }

    public Location getLocationByID(int locationID){
        SQLiteDatabase db = this.getReadableDatabase();
        Location location = new Location();
        String sqlSelect = "SELECT * FROM " + TABLE_LOCATION + " WHERE " + LOCATION_ID + " = " + locationID;
        Cursor cursor = db.rawQuery(sqlSelect, null);
        if(cursor.moveToFirst()){

            return new Location(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }
        return location;
    }

    public Location getLocationByDescription(String locationDescription){
        SQLiteDatabase db = this.getReadableDatabase();
        Location location = new Location();
        String sqlSelect = "SELECT * FROM " + TABLE_LOCATION + " WHERE " + LOCATION_DESCRIPTION + " = " + locationDescription;
        Cursor cursor = db.rawQuery(sqlSelect, null);
        if(cursor.moveToFirst()){

            return new Location(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }
        return location;
    }


}
