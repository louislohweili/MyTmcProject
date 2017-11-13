/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Adapter.RegisterAdapter;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperGuest;
import com.example.louisloh.mytmcproject2.Methods.Guest;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;

public class ListAllRegisterActivity extends AppCompatActivity {
    private DatabaseHelperGuest sQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_register);
        sQLiteHelper = new DatabaseHelperGuest(ListAllRegisterActivity.this);


        ArrayList<Guest> guests = sQLiteHelper.getAllRecords();
        final RegisterAdapter adapter = new RegisterAdapter(this, guests);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guest guest = (Guest) parent.getItemAtPosition(position);
                Toast.makeText(ListAllRegisterActivity.this, "Selected; " + guest.getName(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}