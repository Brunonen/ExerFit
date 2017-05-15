package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Screens;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bruno.exerfit.R;

public class SelectLocationFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.activity_select_location_fragment, container, false);

        Button button = (Button) root.findViewById(R.id.buttonGym);
        button.setOnClickListener(this);
        Button button1 = (Button) root.findViewById(R.id.buttonHome);
        button1.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v)
    {
        Button clickedBtn = (Button)v;
        String buttonText = clickedBtn.getText().toString();

        MainActivity activity = (MainActivity) getActivity();
        activity.setSelectedLocation(buttonText);

        Fragment fragment;
        fragment = new SelectCategoryFragment();

        activity.changeFragment(fragment);

    }

}
