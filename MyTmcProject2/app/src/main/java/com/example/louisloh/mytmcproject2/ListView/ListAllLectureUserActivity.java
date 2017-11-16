/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.ListView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Adapter.LectureRegisterAdapter;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperLectureR;
import com.example.louisloh.mytmcproject2.Methods.Lecture;
import com.example.louisloh.mytmcproject2.ModifyLecturecAcc;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;




public class ListAllLectureUserActivity extends AppCompatActivity {
    private DatabaseHelperLectureR sQLiteHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_lecture_user);

        sQLiteHelper = new DatabaseHelperLectureR(ListAllLectureUserActivity.this);

        ArrayList<Lecture> lecture = sQLiteHelper.getAllRecords();
        final LectureRegisterAdapter adapter = new LectureRegisterAdapter(this, lecture);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lecture lecture = (Lecture) parent.getItemAtPosition(position);
                Toast.makeText(ListAllLectureUserActivity.this, "Selected; " + lecture.getLecture_Name(), Toast.LENGTH_SHORT).show();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyLecturecAcc.class);
                modify_intent.putExtra("Lecture_Name",lecture.getLecture_Name().toString());
                modify_intent.putExtra("Lecture_Email",lecture.getLecture_Email().toString());
                modify_intent.putExtra("Lecture_State", lecture.getLecture_State().toString());
                modify_intent.putExtra("Lecture_Course",lecture.getLecture_Course().toString());
                //modify_intent.putExtra("Lecture_Password",lecture.getLecture_Password().toString());
                startActivity(modify_intent);





            }


        });



    }





}





