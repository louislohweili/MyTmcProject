/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.louisloh.mytmcproject2.ListView.ListEvent;
import com.example.louisloh.mytmcproject2.ListView.ListEventForStaff;
import com.example.louisloh.mytmcproject2.Methods.Event;
import com.example.louisloh.mytmcproject2.Methods.Guest;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;

/**
 * Created by louisloh on 12/9/2017.
 */

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter (Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from
                    (getContext()).inflate(R.layout.event_layout, parent, false);
        }
        TextView textEname = (TextView)convertView.findViewById(R.id.textEname);

        TextView textdt = (TextView)convertView.findViewById(R.id.textdt);



        textEname.setText("Event Name: " + event.getEvent_Name());
        textdt.setText("Date/Time: " + event.getDate_Time());



        return convertView;
    }

}
