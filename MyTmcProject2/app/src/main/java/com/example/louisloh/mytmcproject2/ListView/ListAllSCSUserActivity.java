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

import com.example.louisloh.mytmcproject2.Database.DatabaseHelperScs;
import com.example.louisloh.mytmcproject2.Methods.SCS;
import com.example.louisloh.mytmcproject2.R;
import com.example.louisloh.mytmcproject2.Adapter.SCSListAdapter;

import java.util.ArrayList;

public class ListAllSCSUserActivity extends AppCompatActivity {
    private DatabaseHelperScs sQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_scsuser);
        sQLiteHelper = new DatabaseHelperScs(ListAllSCSUserActivity.this);

        ArrayList<SCS> scss = sQLiteHelper.getAllRecords();
        final SCSListAdapter adapter = new SCSListAdapter(this, scss);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SCS scs = (SCS) parent.getItemAtPosition(position);
                Toast.makeText(ListAllSCSUserActivity.this, "Selected; " + scs.getName(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}