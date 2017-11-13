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

import com.example.louisloh.mytmcproject2.ListView.ListEvent;
import com.example.louisloh.mytmcproject2.Methods.Event;


import java.util.ArrayList;

/**
 * Created by louisloh on 9/9/2017.
 */

public class DatabaseHelperEvent extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Event.db";
    public static final String TABLE_NAME ="Event_table";
    public static final String COL_1 = "Event_ID";
    public static final String COL_2 = "Event_Name";
    public static final String COL_3 = "Date_Time";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    private Context context;
    public DatabaseHelperEvent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ( "
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " VARCHAR  , "
                + COL_3 + "  VARCHAR)"



        );
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void insertRecord(Event event) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, event.getEvent_ID());
        contentValues.put(COL_2, event.getEvent_Name());
        contentValues.put(COL_3, event.getDate_Time());



        long id = database.insert(TABLE_NAME, null, contentValues);

        if (id != -1) {
            event.setEvent_ID("" + id);
            Toast.makeText(context, "New event called " + event.getEvent_Name() + " successfully Register Thank you ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }


    public ArrayList<Event> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Event> events = new ArrayList<Event>();
        Event event;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                event = new Event();
                event.setEvent_ID(cursor.getString(0));
                event.setEvent_Name(cursor.getString(1));
                event.setDate_Time (cursor.getString(2));



                events.add(event);
            }
        }
        cursor.close();
        database.close();

        return events;
    }

    public boolean update(String Event_Name, String Date_Time) {
        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, Event_Name);

        contentValues.put(COL_3, Date_Time);
        // updating row

        db.update(TABLE_NAME, contentValues, " Date = ?", new String[]{Date_Time});
        db.update(TABLE_NAME, contentValues, " Event_Name = ?", new String[]{Event_Name});
        return true;
    }

    /**
     * This method is to delete user record
     *
     * @param
     */
    public boolean deleteEvent(String Event_Name) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Event_Name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Event event = new Event();

        if (cursor.moveToFirst()) {
            event.setEvent_ID(cursor.getString(0));
            db.delete(TABLE_NAME, COL_1 + " = ?",
                    new String[]{String.valueOf(event.getEvent_Name())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Event Check(String Event_Name) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Event_Name + "\"";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Event event = new Event();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            event.setEvent_ID(cursor.getString(0));
            event.setEvent_Name(cursor.getString(1));
            event.setDate_Time(cursor.getString(2));


            cursor.close();
        } else {
            event = null;
        }
        db.close();
        return event;
    }


}


