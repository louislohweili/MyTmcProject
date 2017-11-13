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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaff;
//import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaffR;

public class modifyStaffAcc extends Activity {
    private DatabaseHelperStaff sQLiteHelper;

    EditText editTextStaffName , editTextEmail,editTextPassword , editTextCpassword ;
    Button BtnUpdate , BtnDelete  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_staff_acc);
        sQLiteHelper = new DatabaseHelperStaff (modifyStaffAcc.this);
        editTextStaffName = (EditText) findViewById(R.id.editTextStaffName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCpassword = (EditText) findViewById(R.id.editTextCpassword);

        BtnUpdate = (Button) findViewById(R.id.BtnUpdate);

        BtnDelete = (Button) findViewById(R.id.BtnDelete);
        Intent intent = getIntent() ;

        String Staff_Name= intent.getStringExtra("Staff_Name");
        String Staff_Email= intent.getStringExtra("Staff_Email");
        String Staff_Password= intent.getStringExtra("Staff_Password");
        editTextStaffName.setText(Staff_Name);
        editTextEmail.setText(Staff_Email);
        editTextPassword.setText(Staff_Password);



        BtnUpdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             boolean  isUpdate = sQLiteHelper.updateData(editTextPassword.getText().toString());
                                             if(isUpdate == true)
                                                 Toast.makeText(modifyStaffAcc.this,"Data Update",Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(modifyStaffAcc.this,"Data not Updated",Toast.LENGTH_LONG).show();
                                         }
                                     }
        );


        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(modifyStaffAcc.this);
                builder.setTitle("delete Data Entry");
                builder.setMessage(editTextStaffName.getText() + " Are you sure you want delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean result = sQLiteHelper.deleteUser(
                                editTextStaffName.getText().toString());

                        if (result) {

                            editTextStaffName.setText("");
                            editTextEmail.setText("");
                            editTextPassword.setText("");


                        } else
                            editTextStaffName.setText("No Match Found");

                    }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(modifyStaffAcc.this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }

        });

    }
}
