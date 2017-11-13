//package com.example.louisloh.mytmcproject2.ListView;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.louisloh.mytmcproject2.Adapter.EventAdapter;
//import com.example.louisloh.mytmcproject2.Adapter.TimeTableAdapter;
//import com.example.louisloh.mytmcproject2.ContactUs;
//import com.example.louisloh.mytmcproject2.Database.DatabaseHelperEvent;
//import com.example.louisloh.mytmcproject2.Database.DatabaseHelperStudentR;
//import com.example.louisloh.mytmcproject2.LoginForn.Login;
//import com.example.louisloh.mytmcproject2.MainActivity;
//import com.example.louisloh.mytmcproject2.Methods.Event;
//import com.example.louisloh.mytmcproject2.Methods.Timetable;
//import com.example.louisloh.mytmcproject2.R;
//import com.example.louisloh.mytmcproject2.Register;
//import com.example.louisloh.mytmcproject2.AdminManage.AboutUs;
//import com.example.louisloh.mytmcproject2.AdminManage.Course;
//
//import java.util.ArrayList;
//
//public class ViewNewEvent extends AppCompatActivity {
//    private DatabaseHelperEvent sQLiteHelper;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_new_event);
//        sQLiteHelper = new DatabaseHelperEvent(ViewNewEvent  .this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        ArrayList<Event> events = sQLiteHelper.getAllRecords();
//        EventAdapter adapter = new  EventAdapter(this, events);
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Event event = (Event) parent.getItemAtPosition(position);
//                Toast.makeText(ViewNewEvent.this, "Selected; " + event.getEvent_Name(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//    }
//
//
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater mMenuInflater = getMenuInflater();
//        mMenuInflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_Main:
//                startActivity(new Intent(ViewNewEvent.this, MainActivity.class));
//                return true;
//            case R.id.menu_AboutUs:
//                startActivity(new Intent(ViewNewEvent.this, AboutUs.class));
//                return true;
//            case R.id.menu_Course_string:
//                startActivity(new Intent(ViewNewEvent.this, Course.class));
//                return true;
//            case R.id.menu_View_event:
//                startActivity(new Intent(ViewNewEvent.this, ViewNewEvent.class));
//                return true;
//            case R.id.menu_Register:
//                startActivity(new Intent(ViewNewEvent.this, Register.class));
//                return true;
//            case R.id.menu_ContactUs:
//                startActivity(new Intent(ViewNewEvent.this, ContactUs.class));
//                return true;
//            case R.id.menu_Login:
//                startActivity(new Intent(ViewNewEvent.this, Login.class));
//                return true;
//
//        }
//        return false;
//    }
//}
