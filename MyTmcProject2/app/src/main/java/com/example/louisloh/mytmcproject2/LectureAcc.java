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
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperLectureR;
import com.example.louisloh.mytmcproject2.ListView.ListAllLectureUserActivity;
import com.example.louisloh.mytmcproject2.Methods.Lecture;
import com.example.louisloh.mytmcproject2.Methods.LectureRegister;

public class LectureAcc extends Activity {
    private DatabaseHelperLectureR sQLiteHelper;
    String[] state;
    Spinner spinner;
    String OpStates;
    TextView View;
    EditText editTextName, editTextEmail, editTextCourse, editTextPassword, editTextCPassword;

    Button  btnAdd,  btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_acc);
        sQLiteHelper = new DatabaseHelperLectureR(LectureAcc.this);


        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextCourse = (EditText) findViewById(R.id.editTextCourse);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCPassword = (EditText) findViewById(R.id.editTextCPassword);


        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnSearch = (Button) findViewById(R.id.btnSearch);

        View = (TextView) findViewById(R.id.textViewchoose);


        state = getResources().getStringArray(R.array.state_arrays);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,state);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                OpStates =state[position];

                View.setText("" + OpStates);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                View.setText("Please select ops");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LectureRegister lecture =
                        sQLiteHelper.checkLectureUser(editTextName.getText().toString());


                if (lecture != null) {


                    editTextName.setText(String.valueOf(lecture.getLecture_Name()));
                    editTextEmail.setText(String.valueOf(lecture.getLecture_Email()));
                    editTextCourse.setText(String.valueOf(lecture.getLecture_Course()));
                    View.setText(String.valueOf(lecture.getLecture_State()));
                    editTextPassword.setText(String.valueOf(lecture.getLecture_Password()));




                } else {
                    editTextName.setText("No Match Found");
                }
            }
        });






        btnAdd.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String StrName = editTextName.getText().toString();
                        String StrEmail = editTextEmail.getText().toString();
                        String strCourse = editTextCourse.getText().toString();
                        String StrPassword = editTextPassword.getText().toString();
                        String StrConfirmPassword = editTextCPassword.getText().toString();
                        if (TextUtils.isEmpty(StrName)) {
                            editTextName.setError("Name field cannot be empty");
                            return;
                        }

                        if (TextUtils.isEmpty(StrEmail)) {
                            editTextEmail.setError("Email field cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(strCourse)) {
                            editTextCourse.setError("Course field cannot be empty");
                            return;
                        }
                        if (!StrPassword.equals(StrConfirmPassword)) {
                            Toast.makeText(getApplicationContext(),
                                    "Password does not match", Toast.LENGTH_LONG)
                                    .show();
                            return;
                        }

                        showAlertDialog("");
                    }

                });

    }


    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Data Entry");
        builder.setMessage(editTextName.getText() + " Staff will be added, Continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                String lecture_Name= editTextName.getText().toString();
                String lecture_Email = editTextEmail.getText().toString();
                String lecture_State  = View.getText().toString();

                String lecture_Course = editTextCourse.getText().toString();
                String lecture_Password = editTextPassword.getText().toString();


                Lecture lecture = new Lecture(lecture_Name,lecture_Email,lecture_State,lecture_Course,lecture_Password);
                sQLiteHelper.insertRecord(lecture);

                Intent i = new Intent(LectureAcc.this, ListAllLectureUserActivity.class);
                startActivity(i);

                editTextName.setText("");
                editTextEmail.setText("");
                editTextCourse.setText("");
                View.setText("");
                editTextPassword.setText("");

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LectureAcc.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create();
        builder.show();
    }



}




