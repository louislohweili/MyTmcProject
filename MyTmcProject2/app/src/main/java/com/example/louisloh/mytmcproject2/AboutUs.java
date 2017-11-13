/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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


public class AboutUs extends AppCompatActivity {
    public Button BtnVideo;
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        BtnVideo = (Button) findViewById(R.id.BtnVideo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.tmclogo);
        setSupportActionBar(toolbar);
        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://www.tmc.edu.sg/about-us/");
        myWebView.setWebViewClient(new WebViewClient());

        BtnVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(AboutUs.this, Video.class);
                startActivity(toy);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();
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
                startActivity(new Intent(AboutUs.this, Help.class));
                return true;
            case R.id.menu_Main :
                startActivity(new Intent(AboutUs.this, MainActivity.class));
                return true;
            case R.id.menu_AboutUs :
                startActivity(new Intent(AboutUs.this, AboutUs.class));
                return true;
            case R.id.menu_Course_string :
                startActivity(new Intent(AboutUs.this, Course.class));
                return true;
            case R.id.menu_View_event :
                startActivity(new Intent(AboutUs.this, ListEvent.class));
                return true;

            case R.id.menu_ContactUs:
                startActivity(new Intent(AboutUs.this, ContactUs.class));
                return true;
            case R.id.menu_Login:
                startActivity(new Intent(AboutUs.this, Login.class));
                return true;

        }
        return false;
    }


}
