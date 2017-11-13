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

import com.example.louisloh.mytmcproject2.Adapter.EventAdapter;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperEvent;
import com.example.louisloh.mytmcproject2.Methods.Event;
import com.example.louisloh.mytmcproject2.R;
import com.example.louisloh.mytmcproject2.Register;

import java.util.ArrayList;

public class ListEventForStaff extends AppCompatActivity {
    private DatabaseHelperEvent sQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event_for_staff);
        sQLiteHelper = new DatabaseHelperEvent(ListEventForStaff.this);

        ArrayList<Event> events = sQLiteHelper.getAllRecords();
        EventAdapter adapter = new EventAdapter(this, events);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = (Event) parent.getItemAtPosition(position);
                Toast.makeText(ListEventForStaff.this, "Selected; " + event.getEvent_Name(), Toast.LENGTH_SHORT).show();


            }
        });

    }
}