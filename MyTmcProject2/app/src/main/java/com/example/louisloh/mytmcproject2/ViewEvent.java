//package com.example.louisloh.mytmcproject2;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.ListView;
//
//import com.example.louisloh.mytmcproject2.Adapter.FileListAdapter;
//import com.example.louisloh.mytmcproject2.LoginForn.Login;
//import com.example.louisloh.mytmcproject2.Methods.UploadFile;
//import com.example.louisloh.mytmcproject2.AdminManage.AboutUs;
//import com.example.louisloh.mytmcproject2.AdminManage.Course;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ViewEvent extends AppCompatActivity {
//    private DatabaseReference mDatabaseRef;
//    private List<UploadFile> imgList;
//    private ListView lv;
//    private FileListAdapter adapter;
//    private ProgressDialog progressDialog;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_event);
//        imgList = new ArrayList<>();
//        lv = (ListView) findViewById(R.id.listViewImage);
//        //Show progress dialog during list image loading
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please wait loading list image...");
//        progressDialog.show();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference(Uploadphoto.FB_DATABASE_PATH);
//
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                progressDialog.dismiss();
//
//                //Fetch image data from firebase database
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //ImageUpload class require default constructor
//                    UploadFile img = snapshot.getValue(UploadFile.class);
//                    imgList.add(img);
//                }
//
//
//                //Init adapter
//                adapter = new FileListAdapter(ViewEvent.this, R.layout.image_item, imgList);
//                //Set adapter for listview
//                lv.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//
//
//                progressDialog.dismiss();
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater mMenuInflater =getMenuInflater();
//        mMenuInflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_Main :
//                startActivity(new Intent(ViewEvent.this, MainActivity.class));
//                return true;
//            case R.id.menu_AboutUs :
//                startActivity(new Intent(ViewEvent.this, AboutUs.class));
//                return true;
//            case R.id.menu_Course_string :
//                startActivity(new Intent(ViewEvent.this, Course.class));
//                return true;
//            case R.id.menu_View_event :
//                startActivity(new Intent(ViewEvent.this, ViewEvent.class));
//                return true;
//            case R.id.menu_Register:
//                startActivity(new Intent(ViewEvent.this, Register.class));
//                return true;
//            case R.id.menu_ContactUs:
//                startActivity(new Intent(ViewEvent.this, ContactUs.class));
//                return true;
//            case R.id.menu_Login:
//                startActivity(new Intent(ViewEvent.this, Login.class));
//                return true;
//
//        }
//        return false;
//    }
//
//
//
//}
//
