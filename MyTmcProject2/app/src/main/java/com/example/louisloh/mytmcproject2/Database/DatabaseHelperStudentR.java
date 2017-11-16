/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.Methods.StudentRegister;

import java.util.ArrayList;

/**
 * Created by louisloh on 19/5/2017.
 */

public class DatabaseHelperStudentR extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;
    private Context context;

    public DatabaseHelperStudentR(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public static final String DATABASE_NAME ="StudentLogin.db";
    public static final String TABLE_NAME ="StudentLogin_table";
    public static final String COL_1 ="Student_ID";
    public static final String COL_2 ="Student_Name";
    public static final String COL_3 ="Student_Email";
    public static final String COL_4 ="Student_enrollment_date";
    public static final String COL_5 ="Student_Type ";
    public static final String COL_6 ="Student_Course";
    public static final String COL_7 ="Student_Password";



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ( "
                + COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " VARCHAR NOT NULL UNIQUE, "
                + COL_3 + " VARCHAR, "
                + COL_4 + " VARCHAR,"
                + COL_5 + " VARCHAR, "
                + COL_6+ " VARCHAR, "
                + COL_7+ " VARCHAR)");
    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(StudentRegister studentRegister) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, studentRegister.getStudent_ID());
        contentValues.put(COL_2, studentRegister.getStudent_Name());
        contentValues.put(COL_3, studentRegister.getStudent_Email());
        contentValues.put(COL_4, studentRegister.getStudent_enrollment_date());
        contentValues.put(COL_5, studentRegister.getStudent_Type());
        contentValues.put(COL_6, studentRegister.getStudent_Course());
        contentValues.put(COL_7, studentRegister.getStudent_Password());

        long id = database.insert(TABLE_NAME, null, contentValues);

        if(id != -1) {
            studentRegister.setStudent_ID(""+id);
            Toast.makeText(context, "New event called " + studentRegister.getStudent_Name() + " successfully inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }

    public ArrayList<StudentRegister> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<StudentRegister> studentRegisters = new ArrayList<StudentRegister>();
        StudentRegister studentRegister;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                studentRegister = new StudentRegister();
                studentRegister.setStudent_ID(cursor.getString(0));
                studentRegister.setStudent_Name(cursor.getString(1));
                studentRegister.setStudent_Email(cursor.getString(2));
                studentRegister.setStudent_enrollment_date(cursor.getString(3));
                studentRegister.setStudent_Type(cursor.getString(4));
                studentRegister.setStudent_Course(cursor.getString(5));
                studentRegister.setStudent_Password(cursor.getString(6));



                studentRegisters.add(studentRegister);
            }
        }
        cursor.close();
        database.close();

        return studentRegisters;
    }



    public boolean updateStudentUser(String Student_Password ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COL_7, Student_Password);

        db.update(TABLE_NAME, values, "Student_Password = ?",new String[] { Student_Password });
        return true;
    }

    public boolean deleteUser(String Student_Name) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Student_Name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        StudentRegister studentRegister = new StudentRegister();

        if (cursor.moveToFirst()) {
            studentRegister.setStudent_ID(cursor.getString(0));
            db.delete(TABLE_NAME, COL_1 + " = ?",
                    new String[] { String.valueOf(studentRegister.getStudent_ID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public StudentRegister checkStudentUser(String Name) {

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Name + "\"";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        StudentRegister studentRegister = new StudentRegister();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            studentRegister.setStudent_ID(cursor.getString(0));
            studentRegister.setStudent_Name(cursor.getString(1));
            studentRegister.setStudent_Email(cursor.getString(2));
            studentRegister.setStudent_enrollment_date(cursor.getString(3));
            studentRegister.setStudent_Type(cursor.getString(4));
            studentRegister.setStudent_Course(cursor.getString(5));
            studentRegister.setStudent_Password(cursor.getString(6));

            cursor.close();
        } else {
            studentRegister  = null;
        }
        db.close();
        return  studentRegister ;
    }


    public boolean checkUser(String Name, String password) {

        // array of columns to fetch
        String[] columns = {
                COL_2
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_2 + " = ?" + " AND " + COL_7 + " = ?";

        // selection arguments
        String[] selectionArgs = {Name, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;

    }


}





