package com.planit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Andrew on 7/19/2016.
 */
public class ManageEvents extends Fragment {
    public ManageEvents(){
        // Empty Constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View Manage_Events = inflater.inflate(R.layout.content_main_manage_events, container, false);

        ListView eventsList = (ListView) getActivity().findViewById(R.id.EventList);


        ((MainActivity)getActivity()).showFloatingActionButton();

        return Manage_Events;
    }
}
