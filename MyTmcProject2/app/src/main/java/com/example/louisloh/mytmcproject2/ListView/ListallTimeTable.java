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

import com.example.louisloh.mytmcproject2.Adapter.TimeTableAdapter;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperTimeTable;
import com.example.louisloh.mytmcproject2.Methods.Timetable;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;

public class ListallTimeTable extends AppCompatActivity {
    private DatabaseHelperTimeTable sQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall_time_table);
        sQLiteHelper = new DatabaseHelperTimeTable(ListallTimeTable.this);
        ArrayList<Timetable> timetables = sQLiteHelper.getAllRecords();
      TimeTableAdapter adapter = new  TimeTableAdapter(this, timetables);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Timetable timetable = (Timetable) parent.getItemAtPosition(position);
                Toast.makeText(ListallTimeTable.this, "Selected; " + timetable.getCourse_Name(), Toast.LENGTH_SHORT).show();


            }
        });
    }


}

