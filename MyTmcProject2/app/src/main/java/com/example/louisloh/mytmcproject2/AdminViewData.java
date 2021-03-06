/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.louisloh.mytmcproject2.ListView.ListAllLectureUserActivity;
import com.example.louisloh.mytmcproject2.ListView.ListAllStaffUserActivity;
import com.example.louisloh.mytmcproject2.ListView.ListAllStudentUserActivity;
import com.example.louisloh.mytmcproject2.LoginForn.Login;

public class AdminViewData extends AppCompatActivity {
    Button StudentData, StaffData ,  LectureData ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
     StudentData = (Button) findViewById(R.id.btnStudentData) ;

               StaffData = (Button) findViewById(R.id.btnStaffData) ;
        LectureData = (Button) findViewById(R.id.btnLectureData) ;
       StudentData.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent toy = new Intent(AdminViewData.this, ListAllStudentUserActivity.class);
                startActivity(toy);
            }
        });

        LectureData .setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent toy = new Intent(AdminViewData.this, ListAllLectureUserActivity.class);
                startActivity(toy);

            }
        });



        StaffData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(AdminViewData.this, ListAllStaffUserActivity.class);
                startActivity(toy);
            }
        });


            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater =getMenuInflater();
        mMenuInflater.inflate(R.menu.menuadmin, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Main :
                startActivity(new Intent(AdminViewData.this, AdminMain.class));

                return true;
            case R.id.menu_Manage :
                startActivity(new Intent(AdminViewData.this, AdminManage.class));

                return true;

            case R.id.menu_ListData :
                startActivity(new Intent(AdminViewData.this, AdminViewData.class));

                return true;
            case R.id.menu_Logout:
                startActivity(new Intent(AdminViewData.this, Login.class));

                final ProgressDialog progressDialog = new ProgressDialog(AdminViewData.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Logout ...");
                progressDialog.show();

                return true;


        }
        return false;
    }




}
