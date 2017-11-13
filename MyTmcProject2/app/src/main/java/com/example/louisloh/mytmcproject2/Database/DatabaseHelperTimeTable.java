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
import com.example.louisloh.mytmcproject2.Methods.Timetable;

import java.util.ArrayList;

/**
 * Created by louisloh on 20/6/2017.
 */

public class DatabaseHelperTimeTable extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;
    private Context context;

    public DatabaseHelperTimeTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static final String DATABASE_NAME = "TimeTable.db";
    public static final String TABLE_NAME = "TimeTable_table";
    public static final String COL_1 = "Timetable_ID";
    public static final String COL_2 = "Course_Name";
    public static final String COL_3 = "Date_Time";
    public static final String COL_4 = "Classroom";
    public static final String COL_5 = "Lecture_Name";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ( "
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " VARCHAR , "
                + COL_3 + " VARCHAR , "
                + COL_4 + " VARCHAR, "
                + COL_5 + " VARCHAR )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(Timetable timetable) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, timetable.getTimetable_ID());
        contentValues.put(COL_2, timetable.getCourse_Name());
        contentValues.put(COL_3, timetable.getDate_Time());
        contentValues.put(COL_4, timetable.getClassroom());
        contentValues.put(COL_5, timetable.getLecture_Name());


        long id = database.insert(TABLE_NAME, null, contentValues);

        if (id != -1) {
            timetable.setTimetable_ID("" + id);
            Toast.makeText(context, "New event called " + timetable.getCourse_Name() + " successfully inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }


    public ArrayList<Timetable> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Timetable> timetable = new ArrayList<Timetable>();
        Timetable timetables;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                timetables = new Timetable();
                timetables.setTimetable_ID(cursor.getString(0));
                timetables.setCourse_Name(cursor.getString(1));
                timetables.setDate_Time(cursor.getString(2));
                timetables.setClassroom(cursor.getString(3));
                timetables.setLecture_Name(cursor.getString(4));

                timetable.add(timetables);
            }
        }
        cursor.close();
        database.close();

        return timetable;
    }

    public boolean update(String Course_Name, String Date_Time, String Classroom, String Lecture_Name) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Course_Name);
        contentValues.put(COL_3, Date_Time);
        contentValues.put(COL_4, Classroom);
        contentValues.put(COL_5, Lecture_Name);

        // updating row
        db.update(TABLE_NAME, contentValues, "Course_Name = ?", new String[]{Course_Name});
        db.update(TABLE_NAME, contentValues, "Date_Time = ?", new String[]{Date_Time});
        db.update(TABLE_NAME, contentValues, "Classroom = ?", new String[]{Classroom});
        db.update(TABLE_NAME, contentValues, " Lecture_Name = ?", new String[]{Lecture_Name});
        return true;
    }

    /**
     * This method is to delete user record
     *
     * @param
     */
    public boolean deleteTimeTable(String Course_Name) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Course_Name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Timetable timetable = new Timetable();

        if (cursor.moveToFirst()) {
            timetable.setTimetable_ID(cursor.getString(0));
            db.delete(TABLE_NAME, COL_1 + " = ?",
                    new String[]{String.valueOf(timetable.getTimetable_ID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    /**
     * This method to check user exist or not
     *
     * @param Course_Name
     * @return true/false
     */
    public Timetable Search(String Course_Name) {

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Course_Name + "\"";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Timetable timetables = new Timetable();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            timetables.setTimetable_ID(cursor.getString(0));
            timetables.setCourse_Name(cursor.getString(1));
            timetables.setDate_Time(cursor.getString(2));
            timetables.setClassroom(cursor.getString(3));
            timetables.setLecture_Name(cursor.getString(4));

            cursor.close();
        } else {
            timetables = null;
        }
        db.close();
        return timetables;
    }


}









