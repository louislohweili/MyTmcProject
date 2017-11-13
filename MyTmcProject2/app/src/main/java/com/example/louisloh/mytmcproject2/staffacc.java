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

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaff;
//import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaffR;
import com.example.louisloh.mytmcproject2.ListView.ListAllStaffUserActivity;
import com.example.louisloh.mytmcproject2.Methods.StaffRegister;

public class staffacc extends AppCompatActivity {
    private DatabaseHelperStaff sQLiteHelper;

    EditText editTextStaffName , editTextEmail,editTextPassword , editTextCpassword ;
    Button  Btnadd ,  btnSearch ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffacc);
        sQLiteHelper = new DatabaseHelperStaff (staffacc.this);
        editTextStaffName = (EditText) findViewById(R.id.editTextStaffName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCpassword
 = (EditText) findViewById(R.id.editTextCpassword);

        Btnadd = (Button) findViewById(R.id.Btnadd);

        btnSearch = (Button) findViewById(R.id.btnSearch );





        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StaffRegister staffRegister =
                        sQLiteHelper.checkStaffUser(editTextStaffName.getText().toString());


                if (staffRegister != null) {

                    editTextEmail.setText(String.valueOf(staffRegister.getStaff_Email()));
                    editTextPassword.setText(String.valueOf(staffRegister.getStaff_Password()));



                } else {
                    editTextStaffName.setText("No Match Found");
                }
            }
        });



        Btnadd.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String StrStaff_Name = editTextStaffName.getText().toString();
                        String StrEmail = editTextEmail.getText().toString();
                        String StrPassword = editTextPassword.getText().toString();
                        String StrConfirmPassword = editTextCpassword.getText().toString();
                        if (TextUtils.isEmpty(StrStaff_Name)) {
                            editTextStaffName.setError("Name field cannot be empty");
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
        builder.setMessage(editTextStaffName.getText() + " Staff will be added, Continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String Staff_Name= editTextStaffName.getText().toString();
                String staff_Email = editTextEmail.getText().toString();
                String staff_Password = editTextPassword.getText().toString();



                    StaffRegister staffRegister = new StaffRegister(Staff_Name, staff_Email, staff_Password);
                    sQLiteHelper.insertRecord(staffRegister);


                    Intent i = new Intent(staffacc.this, ListAllStaffUserActivity.class);
                    startActivity(i);

                    editTextStaffName.setText("");
                    editTextEmail.setText("");
                    editTextPassword.setText("");
                }







        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(staffacc.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create();
        builder.show();
    }



}


