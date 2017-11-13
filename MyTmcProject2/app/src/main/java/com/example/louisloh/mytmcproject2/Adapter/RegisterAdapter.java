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

import com.example.louisloh.mytmcproject2.Methods.Guest;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;

/**
 * Created by louisloh on 16/7/2017.
 */

public class RegisterAdapter extends ArrayAdapter<Guest> {
    public RegisterAdapter(Context context, ArrayList<Guest> guests) {
        super(context, 0, guests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Guest guest = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from
                    (getContext()).inflate(R.layout.register_layout, parent, false);
        }

        TextView textName = (TextView) convertView.findViewById(R.id.textName);
        TextView textphone =(TextView) convertView.findViewById(R.id.textphone);
        TextView textEmail =(TextView) convertView.findViewById(R.id.textEmail);
        TextView textdt =(TextView) convertView.findViewById(R.id.textdt);
        TextView textEname =(TextView) convertView.findViewById(R.id.textEname);


        textName.setText("Name: " + guest.getName());
        textphone.setText("Phone: " + guest.getPhone());
        textEmail.setText("Email: " + guest.getEmail());
        textdt.setText("Date_Time: " + guest.getDate_Time());
        textEname.setText("Event_Name  : " + guest.getEvent_Name());


        return convertView;
    }

}