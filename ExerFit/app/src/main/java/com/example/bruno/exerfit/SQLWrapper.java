package com.example.bruno.exerfit;

/**
 * Created by bruno on 01/05/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String CREATE_CATEGORY_TABLE = "CREATE TABLE '" + TABLE_CATEGORY + "'("
                + CATEGORY_ID + " int(11) NOT NULL AUTO_INCREMENT," + CATEGORY_DESCRIPTION + " varchar(45) NOT NULL,"
                + "PRIMARY KEY (`"+CATEGORY_ID+"`),"
                + "UNIQUE KEY `"+CATEGORY_ID+"_UNIQUE` (`"+CATEGORY_ID+"`),"
                + "UNIQUE KEY `"+CATEGORY_DESCRIPTION+"Description_UNIQUE` (`"+CATEGORY_DESCRIPTION+"`)";

        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION + "("
                + LOCATION_ID + " int(11) NOT NULL AUTO_INCREMENT," + LOCATION_DESCRIPTION + " varchar(45) NOT NULL,"
                + "PRIMARY KEY (`"+LOCATION_ID+"`),"
                + "UNIQUE KEY `"+LOCATION_ID+"_UNIQUE` (`"+LOCATION_ID+"`),"
                + "UNIQUE KEY `"+LOCATION_DESCRIPTION+"_UNIQUE` (`"+LOCATION_DESCRIPTION+"`)";

        String CREATE_TYPE_TABLE = "CREATE TABLE " + TABLE_TYPE + "("
                + TYPE_ID + " int(11) NOT NULL AUTO_INCREMENT," + TYPE_DESCRIPTION + " varchar(45) NOT NULL,"
                + "PRIMARY KEY (`"+TYPE_ID+"`),"
                + "UNIQUE KEY `"+TYPE_ID+"_UNIQUE` (`"+TYPE_ID+"`),"
                + "UNIQUE KEY `"+TYPE_DESCRIPTION+"_UNIQUE` (`"+TYPE_DESCRIPTION+"`)";

        String CREATE_TABLE_EXERCISES = "CREATE TABLE '" + TABLE_EXERCISES +"' ("
                +"'"+ EXERCISE_ID +"' int(11) NOT NULL AUTO_INCREMENT,"
                +"'"+ EXERCISE_NAME +"' varchar(45) NOT NULL,"
                +"'"+ EXERCISE_DESCRIPTION +"' longtext NOT NULL,"
                +"'"+ EXERCISE_INTENSITY +"' int(11) NOT NULL,"
                +"'"+ EXERCISE_LOCATION +"' int(11) NOT NULL,"
                +"'"+ EXERCISE_CATEGORY +"' int(11) NOT NULL,"
                +"'"+ EXERCISE_DEFAULT_REPS +"' int(11) DEFAULT NULL,"
                +"'"+ EXERCISE_DEFAULT_WEIGHT_KG +"' int(11) DEFAULT NULL,"
                +"'"+ EXERCISE_DEFAULT_WEIGHT_LBS +"' int(11) DEFAULT NULL,"
                +"'"+ EXERCISE_DEFAULT_DISTANCE_M +"' int(11) DEFAULT NULL,"
                +" PRIMARY KEY (`"+EXERCISE_ID+"`),"
                +"UNIQUE KEY `"+EXERCISE_ID+"_UNIQUE` (`"+EXERCISE_ID+"`),"
                +"UNIQUE KEY `"+EXERCISE_NAME+"_UNIQUE` (`"+EXERCISE_NAME+"'),"
                +"KEY `fk_Location_idx` (`"+EXERCISE_LOCATION+"`),"
                +"KEY `fk_Category_idx` (`"+EXERCISE_CATEGORY+"`),"
                +"CONSTRAINT `fk_Category` FOREIGN KEY (`"+EXERCISE_CATEGORY+"`) REFERENCES `"+TABLE_CATEGORY+"` (`"+CATEGORY_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                +"CONSTRAINT `fk_Location` FOREIGN KEY (`"+EXERCISE_LOCATION+"`) REFERENCES `"+TABLE_LOCATION+"` (`"+LOCATION_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION"
                +")";

        String CREATE_TABLE_WORKOUTS = "CREATE TABLE `"+TABLE_WORKOUTS+"` ("
                +"`"+WORKOUTS_ID+"` int(11) NOT NULL AUTO_INCREMENT,"
                +"`"+WORKOUTS_NAME+"` varchar(45) NOT NULL,"
                +"`"+WORKOUTS_Type+"` int(11) NOT NULL,"
                +"`"+WORKOUTS_LOCATION+"` int(11) NOT NULL,"
                +"`"+WORKOUTS_SETS+"` int(11) DEFAULT NULL,"
                +"`"+WORKOUTS_REST_BETWEEN_SETS+"` int(11) NOT NULL,"
                +"`"+WORKOUTS_REST_BETWEEN_EXERCISES+"` int(11) NOT NULL,"
                +"PRIMARY KEY (`"+WORKOUTS_ID+"`),"
                +"UNIQUE KEY `"+WORKOUTS_ID+"_UNIQUE` (`"+WORKOUTS_ID+"+`),"
                +"UNIQUE KEY `"+WORKOUTS_NAME+"_UNIQUE` (`"+WORKOUTS_NAME+"`),"
                +"KEY `fk_Type_idx` (`"+WORKOUTS_Type+"`),"
                +"KEY `fk_Workout_Location_idx` (`"+WORKOUTS_LOCATION+"`),"
                +"CONSTRAINT `fk_Type` FOREIGN KEY (`"+WORKOUTS_Type+"`) REFERENCES `"+TABLE_TYPE+"` (`"+TYPE_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                +"CONSTRAINT `fk_Workout_Location` FOREIGN KEY (`"+WORKOUTS_LOCATION+"`) REFERENCES `"+TABLE_LOCATION+"` (`"+LOCATION_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION"
                +")";

        String CREATE_TABLE_WORKOUTS_X_EXERCISES = "CREATE TABLE `"+TABLE_WORKOUT_X_EXERCISES+"` ("
                +"`"+W_X_E_WORKOUT_ID+"` int(11) NOT NULL,"
                +"`"+W_X_E_EXERCISE_ID+"` int(11) NOT NULL,"
                +"`"+W_X_E_CUSTOM_REPS+"` int(11) DEFAULT NULL,"
                +"`"+W_X_E_CUSTOM_WEIGHT_KG+"` int(11) DEFAULT NULL,"
                +"`"+W_X_E_CUSTOM_WEIGHT_LBS+"` int(11) DEFAULT NULL,"
                +"`"+W_X_E_CUSTOM_DISTANCE_M+"` int(11) DEFAULT NULL,"
                +"KEY `fk_WorkoutID_idx` (`"+W_X_E_WORKOUT_ID+"`),"
                +"KEY `fk_ExerciseID_idx` (`"+W_X_E_EXERCISE_ID+"`),"
                +"CONSTRAINT `fk_ExerciseID` FOREIGN KEY (`"+W_X_E_EXERCISE_ID+"`) REFERENCES `"+TABLE_EXERCISES+"` (`"+EXERCISE_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                +"CONSTRAINT `fk_WorkoutID` FOREIGN KEY (`"+W_X_E_WORKOUT_ID+"`) REFERENCES `"+TABLE_WORKOUTS+"` (`"+WORKOUTS_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION"
                +")";

        String CREATE_TABLE_SCHEDULE = "CREATE TABLE `"+TABLE_SCHEDULE+"` (" +
                "  `"+SCHEDULE_WORKOUT_ID+"` int(11) NOT NULL," +
                "  `"+SCHEDULE_DAY_OF_THE_WEEK+"` int(11) NOT NULL," +
                "  KEY `fk_WorkoutID_schedule_idx` (`"+SCHEDULE_WORKOUT_ID+"`)," +
                "  CONSTRAINT `fk_WorkoutID_schedule` FOREIGN KEY (`"+SCHEDULE_WORKOUT_ID+"`) REFERENCES `"+TABLE_WORKOUTS+"` (`"+WORKOUTS_ID+"`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
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
}
