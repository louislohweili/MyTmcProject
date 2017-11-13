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

public class ModifyStudentAcc extends Activity {
    private DatabaseHelperStudentR sQLiteHelper;
    private Calendar calendar;

    EditText editTextName, editTextEmail, editTextPassword, editTextCPassword;
    TextView textViewDate, textViewType, textViewcourse;
    Spinner spinnerType, spinnerCourse;
    Button  btnUpdate,btnDelete;
    private int year, month, day;
    String[] Type;
    String[] Course;
    String OpStates;
    String OpStates2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student_acc);
        sQLiteHelper = new DatabaseHelperStudentR(ModifyStudentAcc.this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCPassword = (EditText) findViewById(R.id.editTextCPassword);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewType = (TextView) findViewById(R.id.textViewType);
        textViewcourse = (TextView) findViewById(R.id.textViewcourse);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent() ;
        String Student_Name = intent.getStringExtra("Student_Name");
        String Student_Email = intent.getStringExtra("Student_Email");
        String Student_enrollment_date = intent.getStringExtra("Student_enrollment_date");
        String Student_Type = intent.getStringExtra("Student_Type");
        String Student_Course = intent.getStringExtra("Student_Course");
        String Student_Password = intent.getStringExtra("Student_Password");
        editTextName.setText(Student_Name);
        editTextEmail.setText(Student_Email);
        textViewDate.setText(Student_enrollment_date);
        textViewType.setText(Student_Type);
        textViewcourse.setText(Student_Course);
        editTextPassword.setText(Student_Password);








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


        btnUpdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             boolean isUpdate = sQLiteHelper.updateStudentUser(editTextPassword.getText().toString());
                                             if (isUpdate == true)
                                                 Toast.makeText(ModifyStudentAcc.this, "Data Update", Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(ModifyStudentAcc.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                         }
                                     }
        );


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyStudentAcc.this);
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
                            textViewDate.setText("");
                            textViewType.setText("");
                            textViewcourse.setText("");
                            editTextPassword.setText("");


                        } else
                            editTextName.setText("No Match Found");


                    }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ModifyStudentAcc.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }

        });


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
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };


    private void showDate(int year, int month, int day) {
        textViewDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }





}








