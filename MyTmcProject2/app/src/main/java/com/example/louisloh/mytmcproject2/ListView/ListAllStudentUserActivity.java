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

import com.example.louisloh.mytmcproject2.Adapter.StudentRegisterAdapter;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStudentR;
import com.example.louisloh.mytmcproject2.Methods.StudentRegister;
import com.example.louisloh.mytmcproject2.ModifyStudentAcc;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;

public class ListAllStudentUserActivity extends AppCompatActivity {
    private DatabaseHelperStudentR sQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_student_user);
        sQLiteHelper = new DatabaseHelperStudentR(ListAllStudentUserActivity .this);


        ArrayList<StudentRegister> studentRegisters = sQLiteHelper.getAllRecords();
        StudentRegisterAdapter adapter = new StudentRegisterAdapter(this, studentRegisters);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentRegister student = (StudentRegister) parent.getItemAtPosition(position);
                Toast.makeText(ListAllStudentUserActivity.this, "Selected; " + student.getStudent_Name(), Toast.LENGTH_SHORT).show();
                Intent modify_intent = new Intent(getApplicationContext(), ModifyStudentAcc.class);

                modify_intent.putExtra("Student_Name",student.getStudent_Name().toString());
                modify_intent.putExtra("Student_Email",student.getStudent_Email().toString());
                modify_intent.putExtra("Student_enrollment_date", student.getStudent_enrollment_date().toString());
                modify_intent.putExtra("Student_Type",student.getStudent_Type().toString());
                modify_intent.putExtra("Student_Course",student.getStudent_Course().toString());
                //modify_intent.putExtra("Student_Password",student.getStudent_Password().toString());
                startActivity(modify_intent);



            }
        });
    }


}
