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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.louisloh.mytmcproject2.ListView.ListEvent;
import com.example.louisloh.mytmcproject2.LoginForn.Login;

/**
 * Created by louisloh on 21/9/2017.
 */

public class Course extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.tmclogo);
        setSupportActionBar(toolbar);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings =webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.tmc.edu.sg/courses/");
        webView.setWebViewClient(new WebViewClient());

    }
    @Override
    public void onBackPressed() {

        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
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
                startActivity(new Intent(Course .this, Help.class));
                return true;
            case R.id.menu_Main :
                startActivity(new Intent(Course .this, MainActivity.class));
                return true;
            case R.id.menu_AboutUs :
                startActivity(new Intent(Course .this, AboutUs.class));
                return true;
            case R.id.menu_Course_string :
                startActivity(new Intent(Course .this, Course.class));
                return true;
            case R.id.menu_View_event :
                startActivity(new Intent(Course .this, ListEvent.class));
                return true;

            case R.id.menu_ContactUs:
                startActivity(new Intent(Course .this, ContactUs.class));
                return true;
            case R.id.menu_Login:
                startActivity(new Intent(Course .this, Login.class));
                return true;

        }
        return false;
    }


}