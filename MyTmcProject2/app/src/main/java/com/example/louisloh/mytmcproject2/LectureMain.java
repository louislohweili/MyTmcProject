/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.louisloh.mytmcproject2.ListView.ListUploadPDF;
import com.example.louisloh.mytmcproject2.ListView.ListallTimeTable;
import com.example.louisloh.mytmcproject2.LoginForn.Login;

public class LectureMain extends AppCompatActivity {
    Button timetable, managereg, pdfupload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_main);
        timetable = (Button) findViewById(R.id.btnTimeTable);
        managereg = (Button) findViewById(R.id.btnStudent);
        pdfupload = (Button) findViewById(R.id.btnUpload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        timetable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent toy = new Intent(LectureMain.this, ListallTimeTable.class);
                startActivity(toy);

            }
        });


        managereg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(LectureMain.this, ManageSCSRegister.class);
                startActivity(toy);

            }
        });
        pdfupload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(LectureMain.this, ListUploadPDF.class);
                startActivity(toy);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menustafflogout) {
        MenuInflater mMenuInflater =getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menustafflogout);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_Logout:
                startActivity(new Intent( LectureMain.this, Login.class));
                return true;

        }
        return false;
    }


}