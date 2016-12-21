package com.planit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andrew on 7/25/2016.
 */
public class Manage_Days extends Fragment {

    public Manage_Days(){
        // Empty Constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View Manage_Days = inflater.inflate(R.layout.content_main_manage_days, container, false);

        ((MainActivity)getActivity()).hideFloatingActionButton();

        return Manage_Days;
    }
}
