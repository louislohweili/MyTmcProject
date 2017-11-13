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


import com.example.louisloh.mytmcproject2.Database.DatabaseHelperLectureR;
import com.example.louisloh.mytmcproject2.ListView.ListAllLectureUserActivity;
import com.example.louisloh.mytmcproject2.Methods.Lecture;
import com.example.louisloh.mytmcproject2.Methods.LectureRegister;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;




public class LectureRegisterAdapter extends ArrayAdapter<Lecture> {

    public LectureRegisterAdapter(Context context, ArrayList<Lecture> lectures) {
        super(context, 0, lectures);
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Lecture lecture = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from
                    (getContext()).inflate(R.layout.lecture_user, parent, false);
        }

        final TextView textViewUserName = (TextView)convertView.findViewById(R.id.textViewUserName);
        final TextView textViewEmail = (TextView)convertView.findViewById(R.id.textViewEmail);
        final TextView textView = (TextView)convertView.findViewById(R.id.textViewFP);
        final TextView textViewCourse = (TextView)convertView.findViewById(R.id.textViewCourse);
       // final TextView textViewPassword = (TextView) convertView.findViewById(R.id.textViewPassword);



        textViewUserName.setText("UserName: " + lecture.getLecture_Name());
        textViewEmail.setText("Email: " + lecture.getLecture_Email());
        textView.setText("Type: " + lecture.getLecture_State());
        textViewCourse.setText("Course : " + lecture.getLecture_Course());
       // textViewPassword.setText("Password" + lecture.getLecture_Password());


        return convertView;


    }
}











