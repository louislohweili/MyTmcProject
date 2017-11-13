//package com.example.louisloh.mytmcproject2;
//
//import android.app.ProgressDialog;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.webkit.MimeTypeMap;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.louisloh.mytmcproject2.LoginForn.Login;
//import com.example.louisloh.mytmcproject2.Methods.UploadFile;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class Uploadphoto extends AppCompatActivity {
//
//    private StorageReference mStorageRef;
//    private DatabaseReference mDatabaseRef;
//    private ImageView imageView;
//    private EditText Uploadname;
//    private Uri Upload;
//    public static final String FB_STORAGE_PATH="Upload/";
//    public static final String FB_DATABASE_PATH="Upload/";
//    private static final int Request_Code = 1234;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_uploadevent);
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//        mDatabaseRef= FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
//        imageView = (ImageView) findViewById(R.id.imageView);
//        Uploadname =(EditText) findViewById(R.id.Uploadname);
//    }
//    public void BtnBrowse (View v) {
//        Intent intent = new Intent();
//        intent.setType("Upload/");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Select Upload"),Request_Code);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Request_Code && resultCode == RESULT_OK
//                && data !=null && data.getData() != null);
//        Upload =data.getData();
//        try{
//            Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),Upload);
//            imageView.setImageBitmap(bm);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public String getUploudExt (Uri uri) {
//        ContentResolver contentResolver =getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//    @SuppressWarnings("VisibleForTests")
//    public void BtnUpload(View v) {
//        if (Upload !=null){
//            final ProgressDialog dialog= new ProgressDialog(this);
//            dialog.setTitle("Uploading File");
//            dialog.show();
//            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() +
//                    "."+getUploudExt(Upload));
//
//            ref.putFile(Upload).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    dialog.dismiss();
//                    Toast.makeText(getApplicationContext(),"Upload File",Toast.LENGTH_SHORT).show();
//
//                    UploadFile uploadFile = new UploadFile( Uploadname.getText().toString(),
//                            taskSnapshot.getDownloadUrl().toString());
//                    String UploadID =mDatabaseRef.push().getKey();
//                    mDatabaseRef.child(UploadID).setValue(uploadFile);
//
//                }
//
//            })
//                    .addOnFailureListener(new OnFailureListener() {
//
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            dialog.dismiss();
//                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @SuppressWarnings("VisibleForTests")
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress =(100 * taskSnapshot.getBytesTransferred())
//                                    / taskSnapshot.getTotalByteCount();
//                            dialog.setMessage("Upload file" + (int)progress+"");
//
//                        }
//                    });
//        }
//        else {
//            Toast.makeText(getApplicationContext(),"Pleace Select file",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menustafflogout) {
//        MenuInflater mMenuInflater =getMenuInflater();
//        mMenuInflater.inflate(R.menu.menustafflogout, menustafflogout);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.menu_Logout:
//                startActivity(new Intent(Uploadphoto.this, Login.class));
//
//                final ProgressDialog progressDialog = new ProgressDialog(Uploadphoto.this,
//                        R.style.AppTheme_Dark_Dialog);
//                progressDialog.setIndeterminate(true);
//                progressDialog.setMessage("Logout ...");
//                progressDialog.show();
//
//                return true;
//
//
//        }
//        return false;
//    }
//
//
//}