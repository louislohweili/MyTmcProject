/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.louisloh.mytmcproject2.ListView.ListEvent;
import com.example.louisloh.mytmcproject2.LoginForn.Login;

//import com.example.louisloh.mytmcproject2.ListView.ViewNewEvent;


public class ContactUs extends AppCompatActivity {
    ImageView imageView7;
    Button button4,button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        button = (Button) findViewById(R.id.button);
        button4 = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent toy =new Intent(ContactUs.this,MapsActivity.class);
                startActivity(toy);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent toy =new Intent(ContactUs.this,Facebook.class);
                startActivity(toy);
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
            case R.id.menu_Help :

            case R.id.menu_Main :
                startActivity(new Intent(ContactUs.this, MainActivity.class));
                return true;
            case R.id.menu_AboutUs :
                startActivity(new Intent(ContactUs.this, AboutUs.class));
                return true;
            case R.id.menu_Course_string :
                startActivity(new Intent(ContactUs.this, Course.class));
                return true;
            case R.id.menu_View_event :
                startActivity(new Intent(ContactUs.this, ListEvent.class));

            case R.id.menu_ContactUs:
                startActivity(new Intent(ContactUs.this, ContactUs.class));
                return true;
            case R.id.menu_Login:
                startActivity(new Intent(ContactUs.this, Login.class));
                return true;

        }
        return false;
    }



}