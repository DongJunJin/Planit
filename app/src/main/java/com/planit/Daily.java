package com.planit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andrew on 7/19/2016.
 */
public class Daily extends Fragment {

    public Daily(){
    // Empty Constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View Daily = inflater.inflate(R.layout.content_main_daily, container, false);

        ((MainActivity)getActivity()).hideFloatingActionButton();

        return Daily;
    }
}
