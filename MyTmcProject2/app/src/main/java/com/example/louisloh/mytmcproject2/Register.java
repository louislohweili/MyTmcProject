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
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.louisloh.mytmcproject2.Database.DatabaseHelperGuest;
import com.example.louisloh.mytmcproject2.ListView.ListAllRegisterActivity;
import com.example.louisloh.mytmcproject2.ListView.ListAllStudentUserActivity;
import com.example.louisloh.mytmcproject2.ListView.ListEvent;
import com.example.louisloh.mytmcproject2.LoginForn.Login;
import com.example.louisloh.mytmcproject2.Methods.Guest;

public class Register extends AppCompatActivity {
    private DatabaseHelperGuest sQLiteHelper;
    EditText editTextname, editTextphonono, editTextEmail;
    TextView mDisplayDateTime, editTextEname;
    Calendar mDateAndTime = Calendar.getInstance();
    Button btnadd,view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sQLiteHelper = new DatabaseHelperGuest (Register.this);
        mDisplayDateTime = (TextView) findViewById(R.id.dateTime);
        editTextEname = (TextView) findViewById(R.id.textView27);
        editTextname = (EditText) findViewById(R.id.editTextname);
        editTextphonono = (EditText) findViewById(R.id.editTextphonono);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);



        btnadd = (Button) findViewById(R.id.button10);
        updateDateAndTimeDisplay();

        Intent intent = getIntent() ;
        String Event_Name = intent.getStringExtra("Event_Name");
        String Date_Time = intent.getStringExtra("Date_Time");


        editTextEname.setText(Event_Name);
        mDisplayDateTime .setText(Date_Time);









        btnadd.setOnClickListener(
        new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String StrName = editTextname.getText().toString();
            String Strphone = editTextphonono.getText().toString();
            String StrEmail = editTextEmail.getText().toString();
            String StrEventName = editTextEname.getText().toString();

            if (TextUtils.isEmpty(StrName)) {
                editTextname.setError("Name field cannot be empty");
                return;
            }
            if (TextUtils.isEmpty(Strphone)) {
                editTextphonono.setError("Name field cannot be empty");
                return;
            }

            if (TextUtils.isEmpty(StrEmail)) {
                editTextEmail.setError("Email field cannot be empty");
                return;
            }


            if (TextUtils.isEmpty(StrEventName)) {
                editTextEname.setError("Event Name  field cannot be empty");
                return;

            }



            showAlertDialog("");
        }

    });


    }




    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
        builder.setTitle("Confirm Data Entry");
        builder.setMessage(editTextname.getText() + " Guest will be added, Continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                String name = editTextname.getText().toString();
                String phone = editTextphonono.getText().toString();
                String email = editTextEmail.getText().toString();
                String date_Time = mDisplayDateTime.getText().toString();
                String event_Name = editTextEname.getText().toString();


                Guest guest = new Guest(name,phone,email,date_Time,event_Name);
                sQLiteHelper.insertRecord(guest);

               // Intent i = new Intent(Register.this, ListAllRegisterActivity.class);
               // startActivity(i);

                editTextname.setText("");
                editTextphonono.setText("");
                editTextEmail.setText("");

                mDisplayDateTime.setText("");
                editTextEname.setText("");


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Register.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
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

        new TimePickerDialog(Register.this.getApplicationContext(), mTimeListener,
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

        new DatePickerDialog(Register.this, mDateListener,
                mDateAndTime.get(Calendar.YEAR),
                mDateAndTime.get(Calendar.MONTH),
                mDateAndTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateDateAndTimeDisplay() {
        mDisplayDateTime.setText(DateUtils.formatDateTime(Register.this,
                mDateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_TIME));
    }


}

