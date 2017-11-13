/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.ListView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Adapter.StaffRegisterAdapter;
//import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaffR;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStaff;
import com.example.louisloh.mytmcproject2.Methods.StaffRegister;
import com.example.louisloh.mytmcproject2.R;
import com.example.louisloh.mytmcproject2.modifyStaffAcc;

import java.util.ArrayList;

public class ListAllStaffUserActivity extends AppCompatActivity {
    private DatabaseHelperStaff sQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_staff_user);
        sQLiteHelper = new DatabaseHelperStaff (ListAllStaffUserActivity.this);


        ArrayList<StaffRegister> staffRegisters = sQLiteHelper.getAllRecords();
        final StaffRegisterAdapter adapter = new StaffRegisterAdapter(this, staffRegisters);
        ListView listView = (ListView) findViewById(R.id.listView);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StaffRegister staff = (StaffRegister) parent.getItemAtPosition(position);
                Toast.makeText(ListAllStaffUserActivity.this, "Selected; " + staff.getStaff_Name(), Toast.LENGTH_SHORT).show();



                Intent modify_intent = new Intent(getApplicationContext(), modifyStaffAcc.class);
                modify_intent.putExtra("Staff_Name",staff.getStaff_Name().toString());
                modify_intent.putExtra("Staff_Email",staff.getStaff_Email().toString());
                modify_intent.putExtra("Password", staff.getStaff_Password().toString());
                startActivity(modify_intent);

                    }

                   // @Override
                   // public boolean onQueryTextChange(String text) {
                      //  adapter.getFilter().filter(text);

                       //         sQLiteHelper.checkStaffUser(sv.getQuery().toString());

                       // return false;
                    //}
                //});
       //     }
        });
    }




}