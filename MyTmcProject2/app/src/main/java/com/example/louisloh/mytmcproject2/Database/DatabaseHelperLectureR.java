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

import com.example.louisloh.mytmcproject2.Methods.Lecture;
import com.example.louisloh.mytmcproject2.Methods.LectureRegister;


import java.util.ArrayList;

/**
 * Created by louisloh on 12/5/2017.
 */

public class DatabaseHelperLectureR extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="LectureLogin.db";
    public static final String TABLE_NAME ="LectureLogin_table";
    public static final String COL_1 ="Lecture_ID";
    public static final String COL_2 ="Lecture_Name";
    public static final String COL_3 ="Lecture_Email";
    public static final String COL_4 ="Lecture_State";
    public static final String COL_5 ="Lecture_Course";
    public static final String COL_6 ="Lecture_Password";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    private Context context;
    public DatabaseHelperLectureR(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void  onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" ( "
                + COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " VARCHAR NOT NULL UNIQUE , "
                + COL_3 + " VARCHAR , "
                + COL_4 + " VARCHAR, "
                + COL_5 + " VARCHAR , "
                + COL_6+ " VARCHAR )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(Lecture lecture) {

        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, lecture.getLecture_ID());
        contentValues.put(COL_2, lecture.getLecture_Name());
        contentValues.put(COL_3, lecture.getLecture_Email());
        contentValues.put(COL_4, lecture.getLecture_State());
        contentValues.put(COL_5, lecture.getLecture_Course());
        contentValues.put(COL_6, lecture.getLecture_Password());
        long id = database.insert(TABLE_NAME, null, contentValues);

        if(id != -1) {
            lecture.setLecture_ID(""+id);
            Toast.makeText(context, "New event called " + lecture.getLecture_Name() + " successfully inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }

    public ArrayList<Lecture> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        Lecture lecture;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                lecture = new Lecture();
                lecture.setLecture_ID(cursor.getString(0));
                lecture.setLecture_Name(cursor.getString(1));
                lecture.setLecture_Email(cursor.getString(2));
                lecture.setLecture_State(cursor.getString(3));
                lecture.setLecture_Course(cursor.getString(4));
                lecture.setLecture_Password(cursor.getString(5));

                lectures.add(lecture);
            }
        }
        cursor.close();
        database.close();

        return lectures;
    }











    public boolean updateLectureUser (String Lecture_Course,String Lecture_Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COL_5, Lecture_Course);

        contentValues.put(COL_6,Lecture_Password);

        db.update(TABLE_NAME, contentValues, "Lecture_Password = ?",new String[] { Lecture_Password });
        return true;
    }


    /**
     * This method is to delete user record
     *
     * @param
     */
    public boolean deleteUser(String Lecture_Name) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" +  Lecture_Name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

      LectureRegister lectureRegister = new LectureRegister();

        if (cursor.moveToFirst()) {
            lectureRegister.setLecture_ID(cursor.getString(0));
            db.delete(TABLE_NAME, COL_1 + " = ?",
                    new String[] { String.valueOf(lectureRegister.getLecture_ID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


// find

    public LectureRegister checkLectureUser (String Lecture_Name ) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Lecture_Name + "\"";



        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        LectureRegister lectureRegister = new LectureRegister();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            lectureRegister.setLecture_ID(cursor.getString(0));
            lectureRegister.setLecture_Name(cursor.getString(1));
            lectureRegister.setLecture_Email(cursor.getString(2));
            lectureRegister.setLecture_State(cursor.getString(3));
            lectureRegister.setLecture_Course(cursor.getString(4));
            lectureRegister.setLecture_Password(cursor.getString(5));

            cursor.close();
        } else {
            lectureRegister = null;
        }
        db.close();
        return lectureRegister;
    }
//check user Login

    public boolean checkUser(String Name, String password) {

        // array of columns to fetch
        String[] columns = {
                COL_2
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_2 + " = ?" + " AND " + COL_6 + " = ?";

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








