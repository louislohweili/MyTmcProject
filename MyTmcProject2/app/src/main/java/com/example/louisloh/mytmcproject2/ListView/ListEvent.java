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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.AboutUs;
import com.example.louisloh.mytmcproject2.Adapter.EventAdapter;
import com.example.louisloh.mytmcproject2.ContactUs;
import com.example.louisloh.mytmcproject2.Course;
import com.example.louisloh.mytmcproject2.Database.DatabaseHelperEvent;
import com.example.louisloh.mytmcproject2.Help;
import com.example.louisloh.mytmcproject2.LoginForn.Login;
import com.example.louisloh.mytmcproject2.MainActivity;
import com.example.louisloh.mytmcproject2.Methods.Event;
import com.example.louisloh.mytmcproject2.R;
import com.example.louisloh.mytmcproject2.Register;

import java.util.ArrayList;

public class ListEvent  extends AppCompatActivity {
    private DatabaseHelperEvent sQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);
        sQLiteHelper = new DatabaseHelperEvent(ListEvent.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.tmclogo);
        setSupportActionBar(toolbar);
        ArrayList<Event> events = sQLiteHelper.getAllRecords();
        EventAdapter adapter = new EventAdapter(this, events);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = (Event) parent.getItemAtPosition(position);
                Toast.makeText(ListEvent.this, "Selected; " + event.getEvent_Name(), Toast.LENGTH_SHORT).show();

                Intent modify_intent = new Intent(getApplicationContext(), Register.class);
                modify_intent.putExtra("Event_Name",event.getEvent_Name().toString());
                modify_intent.putExtra("Date_Time",event.getDate_Time().toString());


                startActivity(modify_intent);


            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater =getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Help:
                startActivity(new Intent(ListEvent.this, Help.class));
                return true;
            case R.id.menu_Main :
                startActivity(new Intent(ListEvent.this, MainActivity.class));
                return true;
            case R.id.menu_AboutUs :
                startActivity(new Intent(ListEvent.this, AboutUs.class));
                return true;
            case R.id.menu_Course_string :
                startActivity(new Intent(ListEvent.this, Course.class));
                return true;
            case R.id.menu_View_event :
                startActivity(new Intent(ListEvent.this, ListEvent.class));
                return true;

            case R.id.menu_ContactUs:
                startActivity(new Intent(ListEvent.this, ContactUs.class));
                return true;
            case R.id.menu_Login:
                startActivity(new Intent(ListEvent.this, Login.class));
                return true;

        }
        return false;
    }


}

