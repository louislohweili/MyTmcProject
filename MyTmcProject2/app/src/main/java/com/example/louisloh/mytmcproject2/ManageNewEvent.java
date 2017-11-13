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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperEvent;
import com.example.louisloh.mytmcproject2.ListView.ListEvent;
import com.example.louisloh.mytmcproject2.ListView.ListEventForStaff;
import com.example.louisloh.mytmcproject2.Methods.Event;

public class ManageNewEvent extends Activity {

    private DatabaseHelperEvent sQLiteHelper;
    EditText EventNAME;
    TextView mDisplayDateTime;
    Calendar mDateAndTime = Calendar.getInstance();
    Button Add, Update, Search, Delete, List;
    ImageButton time, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_new_event);
        sQLiteHelper = new DatabaseHelperEvent(ManageNewEvent.this);
        mDisplayDateTime = (TextView) findViewById(R.id.dateTime);
        Add = (Button) findViewById(R.id.btnAdd);


        EventNAME = (EditText) findViewById(R.id.editTextEname);


        time = (ImageButton) findViewById(R.id.button11);
        date = (ImageButton) findViewById(R.id.BtnSeleteDate);

        Update = (Button) findViewById(R.id.btnUpdate);
        Search = (Button) findViewById(R.id.btnSearch);
        Delete = (Button) findViewById(R.id.btnDetele);
        List = (Button) findViewById(R.id.btnView);
        updateDateAndTimeDisplay();
        List.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(ManageNewEvent.this, ListEventForStaff.class);
                startActivity(toy);
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          boolean isUpdate = sQLiteHelper.update(EventNAME.getText().toString(), mDisplayDateTime.getText().toString());
                                          if (isUpdate == true)
                                              Toast.makeText(ManageNewEvent.this, "Data Update", Toast.LENGTH_LONG).show();
                                          else
                                              Toast.makeText(ManageNewEvent.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                      }
                                  }
        );

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Event event = sQLiteHelper.Check(EventNAME.getText().toString());


                if (event != null) {


                    EventNAME.setText(String.valueOf(event.getEvent_Name()));

                    mDisplayDateTime.setText(String.valueOf(event.getDate_Time()));




                } else {
                    EventNAME.setText("No Match Found");
                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ManageNewEvent.this);
                builder.setTitle("Delete Data Entry");
                builder.setMessage(EventNAME.getText() + " Are you sure you want delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean result = sQLiteHelper.deleteEvent(
                                EventNAME.getText().toString());

                        if (result) {


                            EventNAME.setText("");
                            mDisplayDateTime.setText("");



                        } else
                            EventNAME.setText("No Match Found");


                    }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ManageNewEvent.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }

        });

        Add.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String StrEventName = EventNAME.getText().toString();





                        if (TextUtils.isEmpty(StrEventName)) {
                            EventNAME.setError("Event Name  field cannot be empty");
                            return;

                        }



                        showAlertDialog("");
                    }

                });

    }


    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Data Entry");
        builder.setMessage(EventNAME.getText() + " Guest will be added, Continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {


                String event_Name = EventNAME.getText().toString();
                String Date_Time = mDisplayDateTime.getText().toString();



                Event event = new Event(event_Name,Date_Time);
                sQLiteHelper.insertRecord(event);

//                 Intent i = new Intent(Register.this, ListAllRegisterActivity.class);
//                 startActivity(i);

                EventNAME.setText("");
                mDisplayDateTime.setText("");

            


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ManageNewEvent.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
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

        new TimePickerDialog(ManageNewEvent.this, mTimeListener,
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

        new DatePickerDialog(ManageNewEvent.this, mDateListener,
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