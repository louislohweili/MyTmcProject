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

import com.example.louisloh.mytmcproject2.R;
import com.example.louisloh.mytmcproject2.Methods.StaffRegister;

import java.util.ArrayList;

public class StaffRegisterAdapter extends ArrayAdapter<StaffRegister> {
    public StaffRegisterAdapter(Context context, ArrayList<StaffRegister> staffRegisters) {
        super(context, 0, staffRegisters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StaffRegister staff = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from
                    (getContext()).inflate(R.layout.staff_user, parent, false);
        }

        TextView textViewUserName =(TextView) convertView.findViewById(R.id.textViewUserName);
        TextView textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);

       // TextView textViewPassword  = (TextView) convertView.findViewById(R.id.textViewPassword );

        textViewUserName.setText("UserName: " + staff.getStaff_Name());
        textViewEmail.setText("Email: " + staff.getStaff_Email());

       // textViewPassword.setText("Password" +  staff.getStaff_Password());



        return convertView;
    }
}