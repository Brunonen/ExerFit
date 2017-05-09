package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.app.FragmentManager;
import android.app.Fragment;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bruno.exerfit.R;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setupDrawer();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Fragment fragment;
               switch(position) {
                   //Do Workout
                    case 0:
                        fragment = new PreWorkoutFragment();
                        break;
                    // Select Workout
                    case 1:
                        fragment = new PreWorkoutFragment();
                        break;
                    //Workout
                    case 2:
                        fragment = new PreWorkoutFragment();
                        break;
                    //Schedule Workout
                    case 3:
                        fragment = new PreWorkoutFragment();
                       break;
                    //Create Workout
                    case 4:
                        fragment = new PreWorkoutFragment();
                        break;

                    default :
                        fragment = new PreWorkoutFragment();
                        break;
                }
               // Toast.makeText(MainActivity.this, String.valueOf(position) , Toast.LENGTH_SHORT).show();
                // Insert the fragment by replacing any existing fragment
                //Fragment fragment = new PreWorkoutFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                mDrawerLayout.closeDrawer(mDrawerList);

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {


            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }



    private void addDrawerItems() {
        String[] osArray = { "Do Workout", "Select Workout", "Workout", "Schedule Workout", "Create Workout" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }




}