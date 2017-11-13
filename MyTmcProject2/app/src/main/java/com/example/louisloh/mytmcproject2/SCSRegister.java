/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperGuest;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperScs;
import com.example.louisloh.mytmcproject2.ListView.ListAllStudentUserActivity;
import com.example.louisloh.mytmcproject2.Methods.Guest;
import com.example.louisloh.mytmcproject2.Methods.SCS;

public class SCSRegister extends AppCompatActivity {
EditText editTextname ,editTextNRIC, editTextAddress , editTextphonono,editTextEmail, editTextCourse,editTextGY;
    Button Btnadd;
    private DatabaseHelperScs sQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scsregister);
        sQLiteHelper = new DatabaseHelperScs(SCSRegister.this);
      editTextname = (EditText) findViewById(R.id.editTextname);
        editTextNRIC = (EditText)findViewById(R.id.editTextNRIC);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextphonono =(EditText)findViewById(R.id.editTextphonono);
        editTextEmail =(EditText) findViewById(R.id.editTextEmail);
        editTextCourse = (EditText) findViewById(R.id.editTextCourse);
        editTextGY = (EditText) findViewById(R.id.editTextGY);

        Btnadd = (Button) findViewById(R.id.button10);

        Btnadd.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String Name = editTextname.getText().toString();
                        String NRIC = editTextNRIC.getText().toString();
                        String Address = editTextAddress.getText().toString();
                        String Phone= editTextphonono.getText().toString();
                        String Email_Address= editTextEmail.getText().toString();
                        String Course_of_Study= editTextCourse.getText().toString();
                        String Years_of_Graduation= editTextGY.getText().toString();
                        if (TextUtils.isEmpty(Name)) {
                            editTextname.setError("Name field cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(NRIC)){
                            editTextNRIC.setError("NRIC field cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(Address)){
                            editTextAddress.setError("ADDRESS field cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(Phone)) {
                            editTextphonono.setError("Name field cannot be empty");
                            return;
                        }

                        if (TextUtils.isEmpty(Email_Address)) {
                            editTextEmail.setError("Email field cannot be empty");
                            return;
                        }


                        if (TextUtils.isEmpty(Course_of_Study)) {
                            editTextCourse.setError("Course  field cannot be empty");
                            return;

                        }
                        if (TextUtils.isEmpty(Years_of_Graduation)) {
                            editTextGY.setError("Years of Graduation  field cannot be empty");
                            return;

                        }


                        showAlertDialog("");
                    }

                });

    }




    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Data Entry");
        builder.setMessage(editTextname.getText() + " Guest will be added, Continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                String Name = editTextname.getText().toString();
                String NRIC = editTextNRIC.getText().toString();
                String Address = editTextAddress.getText().toString();
                String Phone= editTextphonono.getText().toString();
                String Email_Address= editTextEmail.getText().toString();
                String Course_of_Study= editTextCourse.getText().toString();
                String Years_of_Graduation= editTextGY.getText().toString();

                SCS scs = new SCS(Name,NRIC,Address,Phone,Email_Address,Course_of_Study,Years_of_Graduation);
                sQLiteHelper.insertRecord(scs);

               // Intent i = new Intent(SCSRegister.this, ListAllStudentUserActivity.class);
                //startActivity(i);

                editTextname.setText("");
                editTextNRIC.setText("");
                editTextAddress.setText("");
                editTextphonono.setText("");
                editTextEmail.setText("");
                editTextCourse.setText("");
                editTextGY.setText("");

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SCSRegister.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create();
        builder.show();
    }


    }

