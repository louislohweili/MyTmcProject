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
import com.example.louisloh.mytmcproject2.Methods.StudentRegister;

import java.util.ArrayList;

public class StudentRegisterAdapter extends ArrayAdapter<StudentRegister> {
    public StudentRegisterAdapter(Context context, ArrayList<StudentRegister> studentRegisters) {
        super(context, 0, studentRegisters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StudentRegister student = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from
                    (getContext()).inflate(R.layout.student_user, parent, false);
        }

        TextView textViewUserName = (TextView)convertView.findViewById(R.id.textViewUserName);
        TextView textViewEmail = (TextView)convertView.findViewById(R.id.textViewEmail);
        TextView textViewED = (TextView)convertView.findViewById(R.id.textViewED);
        TextView textViewFP = (TextView)convertView.findViewById(R.id.textViewFP);
        TextView textViewCourse  = (TextView)convertView.findViewById(R.id.textViewCourse );
       // TextView textViewPassword  = (TextView) convertView.findViewById(R.id.textViewPassword );

        textViewUserName.setText("UserName: " + student.getStudent_Name());
        textViewEmail.setText("Email: " + student.getStudent_Email());
        textViewED.setText("enrollment_date: " + student.getStudent_enrollment_date());
        textViewFP.setText("Type: " + student.getStudent_Type());
        textViewCourse .setText("Course : " + student.getStudent_Course());
        //textViewPassword.setText("Password" +  student.getStudent_Password());



        return convertView;
    }
}