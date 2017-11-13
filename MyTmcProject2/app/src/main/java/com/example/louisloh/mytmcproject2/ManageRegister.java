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
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperGuest;
import com.example.louisloh.mytmcproject2.ListView.ListAllRegisterActivity;
import com.example.louisloh.mytmcproject2.Methods.Guest;

public class ManageRegister extends Activity {
    private DatabaseHelperGuest sQLiteHelper;
    EditText editTextname, editTextphonono, editTextEmail, editTextEname;
    TextView mDisplayDateTime;
    Calendar mDateAndTime = Calendar.getInstance();
    Button btnupdate, btnSearch, btndelete, btnlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_register);
        sQLiteHelper = new DatabaseHelperGuest(ManageRegister.this);
        mDisplayDateTime = (TextView) findViewById(R.id.dateTime);

        editTextname = (EditText) findViewById(R.id.editTextname);
        editTextphonono = (EditText) findViewById(R.id.editTextphonono);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextEname = (EditText) findViewById(R.id.editTextEname);

        btnupdate = (Button) findViewById(R.id.button3);
        btnSearch = (Button) findViewById(R.id.button8);
        btndelete = (Button) findViewById(R.id.button7);
        btnlist = (Button) findViewById(R.id.button6);


        updateDateAndTimeDisplay();
        btnlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(ManageRegister.this, ListAllRegisterActivity.class);
                startActivity(toy);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             boolean isUpdate = sQLiteHelper.update(editTextname.getText().toString(),
                                                     editTextphonono.getText().toString(), editTextEmail.getText().toString(),
                                                     mDisplayDateTime.getText().toString(), editTextEname.getText().toString());
                                             if (isUpdate == true)
                                                 Toast.makeText(ManageRegister.this, "Data Update", Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(ManageRegister.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                         }
                                     }
        );


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Guest guest = sQLiteHelper.Check(editTextname.getText().toString());


                if (guest != null) {


                    editTextname.setText(String.valueOf(guest.getName()));
                    editTextphonono.setText(String.valueOf(guest.getPhone()));
                    editTextEmail.setText(String.valueOf(guest.getEmail()));
                    mDisplayDateTime.setText(String.valueOf(guest.getDate_Time()));
                    editTextEname.setText(String.valueOf(guest.getEvent_Name()));


                } else {
                    editTextname.setText("No Match Found");
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ManageRegister.this);
                builder.setTitle("Delete Data Entry");
                builder.setMessage(editTextname.getText() + " Are you sure you want delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean result = sQLiteHelper.deleteGuest(
                                editTextname.getText().toString());

                        if (result) {

                            editTextname.setText("");
                            editTextphonono.setText("");
                            editTextEmail.setText("");

                            mDisplayDateTime.setText("");
                            editTextEname.setText("");


                        } else
                            editTextname.setText("No Match Found");


                    }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ManageRegister.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }

        });

    }

    public void onTimeClicked(View v) {

        TimePickerDialog.OnTimeSetListener mTimeListener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mDateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mDateAndTime.set(Calendar.MINUTE, minute);
                updateDateAndTimeDisplay();
            }
        };

        new TimePickerDialog(ManageRegister.this, mTimeListener,
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

        new DatePickerDialog(ManageRegister.this, mDateListener,
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

