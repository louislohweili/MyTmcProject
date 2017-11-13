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

public class StaffMain extends AppCompatActivity {
Button Uploud,ManageCust,ManageTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        Uploud =(Button) findViewById(R.id.btnUpload);
        ManageCust =(Button) findViewById(R.id.btnCustomer);
        ManageTimeTable = (Button) findViewById(R.id.btnManageTimeTable);



        Uploud.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent toy =new Intent(StaffMain.this,ManageNewEvent.class);
                startActivity(toy);


            }
        });

        ManageCust.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent toy =new Intent(StaffMain.this,ManageRegister.class);
                startActivity(toy);

            }
        });
        ManageTimeTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(StaffMain.this, CreateTimeTable.class);
                startActivity(toy);

            }
        });
    }

  @Override
        public boolean onCreateOptionsMenu(Menu menustafflogout) {
            MenuInflater mMenuInflater =getMenuInflater();
            mMenuInflater.inflate(R.menu.menustafflogout, menustafflogout);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {

                case R.id.menu_Logout:
                    startActivity(new Intent(StaffMain.this, Login.class));

                    final ProgressDialog progressDialog = new ProgressDialog(StaffMain.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Logout ...");
                    progressDialog.show();

                    return true;


            }
            return false;
        }



}



