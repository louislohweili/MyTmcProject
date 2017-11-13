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
import com.example.louisloh.mytmcproject2.Methods.Timetable;

import java.util.ArrayList;

/**
 * Created by louisloh on 4/7/2017.
 */

public class TimeTableAdapter extends ArrayAdapter<Timetable> {
    public TimeTableAdapter(Context context, ArrayList<Timetable> timetables) {
        super(context, 0, timetables);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Timetable timetable = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from
                    (getContext()).inflate(R.layout.timetable_layout, parent, false);
        }


        TextView  Course_Name =(TextView) convertView.findViewById(R.id.textCName);
        TextView Date_Time =(TextView) convertView.findViewById(R.id.txt_td);
        TextView Classroom= (TextView)convertView.findViewById(R.id.txt_CR);
        TextView Lecture_Name =(TextView) convertView.findViewById(R.id.txt_LName);

        Course_Name.setText(timetable.getCourse_Name());
        Date_Time.setText(timetable.getDate_Time());
        Classroom.setText(timetable.getClassroom());
        Lecture_Name.setText(timetable.getLecture_Name());



        return convertView;
    }
}