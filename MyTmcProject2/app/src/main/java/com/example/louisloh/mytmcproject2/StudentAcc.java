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
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStudentR;
import com.example.louisloh.mytmcproject2.ListView.ListAllStudentUserActivity;
import com.example.louisloh.mytmcproject2.Methods.StudentRegister;

import java.util.ArrayList;
import java.util.List;

public class StudentAcc extends Activity  {

    private DatabaseHelperStudentR sQLiteHelper;
    private Calendar calendar;

    EditText editTextName, editTextEmail, editTextPassword, editTextCPassword;
    TextView textViewDate, textViewType, textViewcourse;
    Spinner spinnerType, spinnerCourse;
    Button btnadd,  btnSearch;
    private int year, month, day;
    String[] Type;
    String[] Course;
    String OpStates;
    String OpStates2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_acc);
        sQLiteHelper = new DatabaseHelperStudentR(StudentAcc.this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCPassword = (EditText) findViewById(R.id.editTextCPassword);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewType = (TextView) findViewById(R.id.textViewType);
        textViewcourse = (TextView) findViewById(R.id.textViewcourse);



        btnadd = (Button) findViewById(R.id.btnadd);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);


        Type = getResources().getStringArray(R.array.Type_arrays);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Type);
        spinnerType.setAdapter(dataAdapter);



        Course = getResources().getStringArray(R.array.Course_arrays);
        spinnerCourse= (Spinner) findViewById(R.id.spinnerCourse);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Course);
        spinnerCourse.setAdapter(dataAdapter2);



        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                OpStates =Type [position];

                textViewType.setText("" + OpStates);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textViewType.setText("Please select ops");
            }
        });

        spinnerCourse .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                OpStates2 = Course [position];

                textViewcourse.setText("" + OpStates2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textViewcourse.setText("Please select ops");
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StudentRegister student = sQLiteHelper.checkStudentUser(editTextName.getText().toString());


                if (student != null) {


                    editTextName.setText(String.valueOf(student.getStudent_Name()));
                    editTextEmail.setText(String.valueOf(student.getStudent_Email()));
                    textViewDate.setText(String.valueOf(student.getStudent_enrollment_date()));
                    textViewType.setText(String.valueOf(student.getStudent_Type()));
                    textViewcourse.setText(String.valueOf(student.getStudent_Course()));
                    editTextPassword.setText(String.valueOf(student.getStudent_Password()));
                    textViewcourse.setText("Please select ops");

                } else {
                    editTextName.setText("No Match Found");
                }
            }
        });


        btnadd.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String StrName = editTextName.getText().toString();
                        String StrEmail = editTextEmail.getText().toString();
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

                String Student_Name = editTextName.getText().toString();
                String Student_Email = editTextEmail.getText().toString();
                String Student_enrollment_date = textViewDate.getText().toString();

                String Student_Type = textViewType.getText().toString();
                String Student_Course = textViewcourse.getText().toString();
                String Student_Password = editTextPassword.getText().toString();


                StudentRegister student = new StudentRegister(Student_Name, Student_Email, Student_enrollment_date, Student_Type, Student_Course, Student_Password);
                sQLiteHelper.insertRecord(student);

                Intent i = new Intent(StudentAcc.this, ListAllStudentUserActivity.class);
                startActivity(i);

                editTextName.setText("");
                editTextEmail.setText("");
                textViewDate.setText("");
              textViewType.setText("");
                textViewcourse.setText("");
                editTextPassword.setText("");

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StudentAcc.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create();
        builder.show();
    }


    @SuppressWarnings("deprecation")
    public void SetDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        textViewDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


}