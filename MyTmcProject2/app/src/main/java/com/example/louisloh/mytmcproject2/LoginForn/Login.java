/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.LoginForn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.AboutUs;
import com.example.louisloh.mytmcproject2.AdminMain;
import com.example.louisloh.mytmcproject2.ContactUs;
import com.example.louisloh.mytmcproject2.Course;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperLectureR;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaff;
//import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaffR;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStudentR;
import com.example.louisloh.mytmcproject2.Help;
import com.example.louisloh.mytmcproject2.LectureMain;

import com.example.louisloh.mytmcproject2.ListView.ListEvent;
import com.example.louisloh.mytmcproject2.MainActivity;
import com.example.louisloh.mytmcproject2.R;
import com.example.louisloh.mytmcproject2.StaffMain;
import com.example.louisloh.mytmcproject2.StudentMain;

public class Login extends AppCompatActivity {
    private DatabaseHelperLectureR sQLiteHelperL;
    private DatabaseHelperStudentR sQLiteHelperSTD;
    private DatabaseHelperStaff sQLiteHelper;

    Button b1;
    EditText ed1,ed2;
    TextView tx1;
    int counter = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sQLiteHelperL = new DatabaseHelperLectureR(Login.this);
        sQLiteHelperSTD = new DatabaseHelperStudentR(Login.this);
        sQLiteHelper = new DatabaseHelperStaff(Login.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.tmclogo);
        setSupportActionBar(toolbar);


        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = ed1.getText().toString();
                String Password = ed2.getText().toString();
                if (TextUtils.isEmpty(Username)) {
                    ed1.setError("UserName field cannot be empty");
                    ed1.requestFocus();
                }
                if (TextUtils.isEmpty(Password)) {
                    ed2.setError("Password cannot be empty");
                    ed2.requestFocus();
                } else if (ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {

                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Welcome to TMC adminstrator...");
                    progressDialog.show();
                    Intent toy = new Intent(Login.this, AdminMain.class);
                    startActivity(toy);
                } else if (sQLiteHelperL.checkUser(ed1.getText().toString().trim()
                        , ed2.getText().toString().trim())) {


                    Intent toy = new Intent(Login.this, LectureMain.class);
                    toy.putExtra("Name", ed1.getText().toString().trim());

                    startActivity(toy);


                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Welcome to TMC Lecture ...");
                    progressDialog.show();
                } else if (sQLiteHelperSTD.checkUser(ed1.getText().toString().trim()
                        , ed2.getText().toString().trim())) {


                    Intent toys = new Intent(Login.this, StudentMain.class);
                    toys.putExtra("Name", ed1.getText().toString().trim());

                    startActivity(toys);



                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                            R.style.AppTheme_Dark_Dialog);

                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Welcome to TMC Student ...");
                    progressDialog.show();
                } else if (sQLiteHelper.checkUser(ed1.getText().toString().trim()
                        , ed2.getText().toString().trim())) {


                    Intent toy = new Intent(Login.this, StaffMain.class);
                    toy.putExtra("Name", ed1.getText().toString().trim());

                    startActivity(toy);


                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Welcome to TMC Staff...");
                    progressDialog.show();



                }else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));
                    if (counter == 0) {
                        b1.setEnabled(false);
                    }


                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater =getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Help:
                startActivity(new Intent(Login.this, Help.class));
                return true;
            case R.id.menu_Main :
                startActivity(new Intent(Login.this, MainActivity.class));
                return true;
            case R.id.menu_AboutUs :
                startActivity(new Intent(Login.this, AboutUs.class));
                return true;
            case R.id.menu_Course_string :
                startActivity(new Intent(Login.this, Course.class));
                return true;
            case R.id.menu_View_event :
                startActivity(new Intent(Login.this, ListEvent.class));
                return true;

            case R.id.menu_ContactUs:
                startActivity(new Intent(Login.this, ContactUs.class));
                return true;
            case R.id.menu_Login:
                startActivity(new Intent(Login.this, Login.class));
                return true;

        }
        return false;
    }

}