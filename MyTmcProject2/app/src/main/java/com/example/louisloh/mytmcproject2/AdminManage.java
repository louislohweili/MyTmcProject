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

import com.example.louisloh.mytmcproject2.LoginForn.Login;

public class AdminManage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Button btnstaff = (Button) findViewById(R.id.btnstaff);
        Button btnlecture = (Button) findViewById(R.id.btnlecture);
        Button btnstudend = (Button) findViewById(R.id.btnstudend);
        btnstaff.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent toy = new Intent(AdminManage.this, staffacc.class);
                startActivity(toy);
            }
        });


        btnlecture.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent toy = new Intent(AdminManage.this, LectureAcc.class);
                startActivity(toy);
            }
        });
        btnstudend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent toy = new Intent(AdminManage.this, StudentAcc.class);
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
            case R.id.menu_Main:
                startActivity(new Intent(AdminManage.this, AdminMain.class));

                return true;
            case R.id.menu_Manage:
                startActivity(new Intent(AdminManage.this, AdminManage.class));

                return true;

            case R.id.menu_ListData:
                startActivity(new Intent(AdminManage.this, AdminViewData.class));

                return true;
            case R.id.menu_Logout:
                startActivity(new Intent(AdminManage.this, Login.class));

                final ProgressDialog progressDialog = new ProgressDialog(AdminManage.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Logout ...");
                progressDialog.show();

                return true;


        }
        return false;
    }






}