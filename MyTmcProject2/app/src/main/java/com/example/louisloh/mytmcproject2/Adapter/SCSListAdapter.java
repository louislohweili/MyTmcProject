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

import com.example.louisloh.mytmcproject2.Methods.SCS;
import com.example.louisloh.mytmcproject2.R;


import java.util.ArrayList;

/**
 * Created by louisloh on 17/7/2017.
 */

public class SCSListAdapter extends ArrayAdapter<SCS> {
    public SCSListAdapter(Context context, ArrayList<SCS> scss) {
        super(context, 0, scss);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SCS scs = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from
                    (getContext()).inflate(R.layout.scs_layout, parent, false);
        }

        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView textViewNRIC = (TextView) convertView.findViewById(R.id.textViewNRIC);
        TextView textViewAddress = (TextView) convertView.findViewById(R.id.textViewAddress);
        TextView textViewP = (TextView) convertView.findViewById(R.id.textViewP);


        TextView textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
        TextView textViewCS = (TextView) convertView.findViewById(R.id.textViewCS);
        TextView textViewYG = (TextView) convertView.findViewById(R.id.textViewYG);

        textViewName.setText("Name: " + scs.getName());
        textViewNRIC.setText("NRIC:" + scs.getNRIC());
        textViewAddress.setText("Address: " + scs.getAddress());
        textViewP.setText("Phone No:" + scs.getPhone());
        textViewEmail.setText("Email:" + scs.getEmail_Address());

        textViewCS.setText("Course of Study:" + scs.getCourse_of_Study());
        textViewYG.setText("Years_of_Graduation" + scs.getYears_of_Graduation());
        return convertView;
    }

}