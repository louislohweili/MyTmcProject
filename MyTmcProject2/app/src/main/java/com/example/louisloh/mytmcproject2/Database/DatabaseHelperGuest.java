/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Database;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.louisloh.mytmcproject2.ListView.ListAllRegisterActivity;
import com.example.louisloh.mytmcproject2.Methods.Guest;
import com.example.louisloh.mytmcproject2.Methods.Timetable;
import com.example.louisloh.mytmcproject2.Register;

import java.util.ArrayList;

/**
 * Created by louisloh on 5/7/2017.
 */

public class DatabaseHelperGuest extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;
    private Context context;

    public DatabaseHelperGuest(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static final String DATABASE_NAME = "Guest.db";
    public static final String TABLE_NAME = "Guest_table";
    public static final String COL_1 = "Guest_id";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Phone";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Date_Time";
    public static final String COL_6 = "Event_Name_Guest";




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ( "
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " VARCHAR NOT NULL UNIQUE, "
                + COL_3 + " VARCHAR, "
                + COL_4 + " VARCHAR,"
                + COL_5 + " VARCHAR, "
                + COL_6 + " VARCHAR)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void insertRecord(Guest guest) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, guest.getGuest_id());
        contentValues.put(COL_2, guest.getName());
        contentValues.put(COL_3, guest.getPhone());
        contentValues.put(COL_4, guest.getEmail());
        contentValues.put(COL_5, guest.getDate_Time());
        contentValues.put(COL_6, guest.getEvent_Name());


        long id = database.insert(TABLE_NAME, null, contentValues);

        if (id != -1) {
            guest.setGuest_id("" + id);
            Toast.makeText(context, "New event called " + guest.getName() + " successfully Register Thank you ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }


    public ArrayList<Guest> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Guest> guests = new ArrayList<Guest>();
        Guest guest;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                guest = new Guest();
                guest.setGuest_id(cursor.getString(0));
                guest.setName(cursor.getString(1));
                guest.setPhone(cursor.getString(2));
                guest.setEmail(cursor.getString(3));
                guest.setDate_Time(cursor.getString(4));
                guest.setEvent_Name(cursor.getString(5));

                guests.add(guest);
            }
        }
        cursor.close();
        database.close();

        return guests;
    }

    public boolean update(String Name, String Phone, String Email, String Date_Time, String Event_Name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, Name);
        contentValues.put(COL_3, Phone);
        contentValues.put(COL_4, Email);
        contentValues.put(COL_5, Date_Time);
        contentValues.put(COL_6, Event_Name);
        // updating row
        db.update(TABLE_NAME, contentValues, "Name = ?", new String[]{Name});
        db.update(TABLE_NAME, contentValues, "Phone = ?", new String[]{Phone});
        db.update(TABLE_NAME, contentValues, "Email = ?", new String[]{Email});
        db.update(TABLE_NAME, contentValues, " Date_Time = ?", new String[]{Date_Time});
        db.update(TABLE_NAME, contentValues, " Event_Name = ?", new String[]{Event_Name});
        return true;
    }

    /**
     * This method is to delete user record
     *
     * @param
     */
    public boolean deleteGuest(String Name) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Guest guest = new Guest();

        if (cursor.moveToFirst()) {
            guest.setGuest_id(cursor.getString(0));
            db.delete(TABLE_NAME, COL_1 + " = ?",
                    new String[]{String.valueOf(guest.getGuest_id())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Guest Check(String Name) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Name + "\"";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Guest guest = new Guest();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            guest.setGuest_id(cursor.getString(0));
            guest.setName(cursor.getString(1));
            guest.setPhone(cursor.getString(2));
            guest.setEmail(cursor.getString(3));
            guest.setDate_Time(cursor.getString(4));
            guest.setEvent_Name(cursor.getString(5));

            cursor.close();
        } else {
            guest = null;
        }
        db.close();
        return guest;
    }


}










