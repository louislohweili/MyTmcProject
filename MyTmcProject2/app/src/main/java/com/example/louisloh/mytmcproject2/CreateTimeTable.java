/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperTimeTable;
import com.example.louisloh.mytmcproject2.ListView.ListallTimeTable;
import com.example.louisloh.mytmcproject2.Methods.Guest;
import com.example.louisloh.mytmcproject2.Methods.Timetable;

public class CreateTimeTable extends AppCompatActivity {
    private DatabaseHelperTimeTable sQLiteHelper;
    Button add, update, search, delete, List;
    EditText editTextSName, editTextClassroom, editTextLecture;
    TextView mDisplayDateTime;
    Calendar mDateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time_table);
        sQLiteHelper = new DatabaseHelperTimeTable(CreateTimeTable.this);
        editTextSName = (EditText) findViewById(R.id.editTextSName);
        editTextClassroom = (EditText) findViewById(R.id.editTextClassroom);
        editTextLecture = (EditText) findViewById(R.id.editTextLecture);
        mDisplayDateTime = (TextView) findViewById(R.id.dateTime);
        updateDateAndTimeDisplay();
        add = (Button) findViewById(R.id.add);
        update = (Button) findViewById(R.id.Update);
        search = (Button) findViewById(R.id.Search);
        delete = (Button) findViewById(R.id.Detele);
        List = (Button) findViewById(R.id.List);

        delete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateTimeTable.this);
                builder.setTitle("Delete Data Entry");
                builder.setMessage(editTextSName.getText() + " Are you sure you want delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean result = sQLiteHelper.deleteTimeTable(
                                editTextSName.getText().toString());

                        if (result) {

                            editTextSName.setText("");
                            mDisplayDateTime.setText("");
                            editTextClassroom.setText("");
                            editTextLecture.setText("");


                        } else
                            editTextSName.setText("No Match Found");


                    }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CreateTimeTable.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }

        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Timetable timetable = sQLiteHelper.Search(editTextSName.getText().toString());


                if (timetable!= null) {

                    editTextSName.setText(String.valueOf(timetable.getCourse_Name()));
                    mDisplayDateTime.setText(String.valueOf(timetable.getDate_Time()));
                    editTextClassroom.setText(String.valueOf(timetable.getClassroom()));
                    editTextLecture.setText(String.valueOf(timetable.getLecture_Name()));


                } else {
                    editTextSName.setText("No Match Found");
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = sQLiteHelper.update(editTextSName.getText().toString(),
                        editTextClassroom.getText().toString(), editTextLecture.getText().toString(),
                        mDisplayDateTime.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(CreateTimeTable.this, "Data Update", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CreateTimeTable.this, "Data not Updated", Toast.LENGTH_LONG).show();
            }
            }
        );
                add.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String StrSubjectName = editTextSName.getText().toString();
                        String StrClassroom = editTextClassroom.getText().toString();
                        String StrLectureName = editTextLecture.getText().toString();

                        if (TextUtils.isEmpty(StrSubjectName)) {
                            editTextSName.setError("Name field cannot be empty");
                            return;
                        }

                        if (TextUtils.isEmpty(StrLectureName)) {
                            editTextClassroom.setError("Email field cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(StrClassroom)) {
                            editTextLecture.setError("Email field cannot be empty");
                            return;
                        }


                        showAlertDialog("");
                    }

                });

    }


    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Data Entry");
        builder.setMessage(editTextSName.getText() + " TimeTable Will be added, Continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String Course_Name = editTextSName.getText().toString();
                String Date_Time = mDisplayDateTime.getText().toString();
                String Classroom = editTextClassroom.getText().toString();
                String Lecture_Name = editTextLecture.getText().toString();


                Timetable timetable = new Timetable(Course_Name, Date_Time, Classroom, Lecture_Name);
                sQLiteHelper.insertRecord(timetable);

                Intent i = new Intent(CreateTimeTable.this, ListallTimeTable.class);
                startActivity(i);

                editTextSName.setText("");
                mDisplayDateTime.setText("");
                editTextClassroom.setText("");
                editTextLecture.setText("");

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CreateTimeTable.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create();
        builder.show();
    }





    public void onTimeClicked(View v) {

        TimePickerDialog.OnTimeSetListener mTimeListener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mDateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mDateAndTime.set(Calendar.MINUTE, minute);
                updateDateAndTimeDisplay();
            }
        };

        new TimePickerDialog(CreateTimeTable.this, mTimeListener,
                mDateAndTime.get(Calendar.HOUR_OF_DAY),
                mDateAndTime.get(Calendar.MINUTE), true).show();

    }

    public void onDateClicked(View v) {

        DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mDateAndTime.set(Calendar.YEAR, year);
                mDateAndTime.set(Calendar.MONTH, monthOfYear);
                mDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateAndTimeDisplay();
            }
        };

        new DatePickerDialog(CreateTimeTable.this, mDateListener,
                mDateAndTime.get(Calendar.YEAR),
                mDateAndTime.get(Calendar.MONTH),
                mDateAndTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateDateAndTimeDisplay() {
        mDisplayDateTime.setText(DateUtils.formatDateTime(this,
                mDateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_TIME));
    }





}





