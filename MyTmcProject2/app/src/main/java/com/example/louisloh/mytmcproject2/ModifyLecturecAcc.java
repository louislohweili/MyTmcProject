/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperLectureR;

public class ModifyLecturecAcc extends Activity {
    private DatabaseHelperLectureR sQLiteHelper;
    String[] States;
    Spinner spinner;
    String OpStates;
    TextView View;
    EditText editTextName, editTextEmail, editTextCourse, editTextPassword, editTextCPassword;

    Button btnUpdate, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_lecturec_acc);
        sQLiteHelper = new DatabaseHelperLectureR (ModifyLecturecAcc.this);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextCourse = (EditText) findViewById(R.id.editTextCourse);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCPassword = (EditText) findViewById(R.id.editTextCPassword);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        View = (TextView) findViewById(R.id.textViewchoose);

        Intent intent = getIntent() ;
        String Lecture_Name= intent.getStringExtra("Lecture_Name");
        String Lecture_Email= intent.getStringExtra("Lecture_Email");
        String Lecture_State = intent.getStringExtra("Lecture_State");
        String Lecture_Course = intent.getStringExtra("Lecture_Course");
        String Lecture_Password = intent.getStringExtra("Lecture_Password");
        editTextName.setText(Lecture_Name);
        editTextEmail.setText(Lecture_Email);
        editTextCourse.setText(Lecture_Course);
        View.setText(Lecture_State);
        editTextPassword.setText(Lecture_Password);


        States = getResources().getStringArray(R.array.state_arrays);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, States);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                OpStates = States[position];

                View.setText("" + OpStates);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                View.setText("Please select ops");
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             boolean isUpdate = sQLiteHelper.updateLectureUser(editTextCourse.getText().toString(),

                                                     editTextPassword.getText().toString());
                                             if (isUpdate == true)
                                                 Toast.makeText(ModifyLecturecAcc.this, "Data Update", Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(ModifyLecturecAcc.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                         }
                                     }
        );


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyLecturecAcc.this);
                builder.setTitle("Delete Data Entry");
                builder.setMessage(editTextName.getText() + " Are you sure you want delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean result = sQLiteHelper.deleteUser(
                                editTextName.getText().toString());

                        if (result) {

                            editTextName.setText("");
                            editTextEmail.setText("");
                            editTextCourse.setText("");
                            View.setText("");
                            editTextPassword.setText("");


                        } else
                            editTextName.setText("No Match Found");


                    }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ModifyLecturecAcc.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }

        });


    }



}




