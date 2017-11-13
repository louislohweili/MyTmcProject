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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperScs;
import com.example.louisloh.mytmcproject2.ListView.ListAllSCSUserActivity;
import com.example.louisloh.mytmcproject2.Methods.SCS;

public class ManageSCSRegister extends AppCompatActivity {
    EditText editTextname ,editTextNRIC, editTextAddress , editTextphonono,editTextEmail, editTextCourse,editTextGY;
    Button btnupdate, btnSearch, btndelete, btnlist;
    private DatabaseHelperScs sQLiteHelper;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_scsregister);
        sQLiteHelper = new DatabaseHelperScs(ManageSCSRegister.this);

        editTextname = (EditText) findViewById(R.id.editTextname);
        editTextNRIC = (EditText)findViewById(R.id.editTextNRIC);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextphonono =(EditText)findViewById(R.id.editTextphonono);
        editTextEmail =(EditText) findViewById(R.id.editTextEmail);
        editTextCourse = (EditText) findViewById(R.id.editTextCourse);
        editTextGY = (EditText) findViewById(R.id.editTextGY);

        btnupdate = (Button) findViewById(R.id.button3);
        btnSearch = (Button) findViewById(R.id.button8);
        btndelete = (Button) findViewById(R.id.button7);
        btnlist = (Button) findViewById(R.id.button6);

        btnlist.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent toy =new Intent(ManageSCSRegister .this,ListAllSCSUserActivity.class);
                startActivity(toy);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             boolean isUpdate = sQLiteHelper.updateData(editTextname.getText().toString(),editTextNRIC.getText().toString(),
                                                     editTextAddress.getText().toString(),editTextphonono.getText().toString(),
                                                     editTextEmail.getText().toString(),
                                                     editTextCourse.getText().toString(), editTextGY.getText().toString());
                                             if (isUpdate == true)
                                                 Toast.makeText(ManageSCSRegister.this, "Data Update", Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(ManageSCSRegister.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                         }
                                     }
        );


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SCS scs = sQLiteHelper.checkUser(editTextname.getText().toString());


                if (scs != null) {


                    editTextname.setText(String.valueOf(scs.getName()));
                    editTextNRIC.setText(String.valueOf(scs.getNRIC()));
                    editTextAddress.setText(String.valueOf(scs.getAddress()));
                    editTextphonono.setText(String.valueOf(scs.getPhone()));
                    editTextEmail.setText(String.valueOf(scs.getEmail_Address()));
                    editTextCourse.setText(String.valueOf(scs.getCourse_of_Study()));
                    editTextGY.setText(String.valueOf(scs.getYears_of_Graduation()));


                } else {
                    editTextname.setText("No Match Found");
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ManageSCSRegister .this);
                builder.setTitle("Delete Data Entry");
                builder.setMessage(editTextname.getText() + " Are you sure you want delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean result = sQLiteHelper.deleteUser(
                                editTextname.getText().toString());

                        if (result) {

                            editTextname.setText("");
                            editTextNRIC.setText("");
                            editTextAddress.setText("");
                            editTextphonono.setText("");
                            editTextEmail.setText("");
                            editTextCourse.setText("");
                            editTextGY.setText("");


                        } else
                            editTextname.setText("No Match Found");


                    }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ManageSCSRegister .this, "Adding Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }

        });

    }
}
