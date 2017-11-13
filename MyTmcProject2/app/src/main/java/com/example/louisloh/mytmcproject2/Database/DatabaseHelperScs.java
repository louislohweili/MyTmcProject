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

import com.example.louisloh.mytmcproject2.Methods.SCS;
import com.example.louisloh.mytmcproject2.Methods.StaffRegister;

import java.util.ArrayList;

/**
 * Created by louisloh on 17/7/2017.
 */

public class DatabaseHelperScs extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;
    private Context context;

    public DatabaseHelperScs (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public static final String DATABASE_NAME ="SCS.db";
    public static final String TABLE_NAME ="SCS_table";
    public static final String COL_1 ="SCS_ID";
    public static final String COL_2 ="Name";
    public static final String COL_3 ="NRIC";
    public static final String COL_4 ="Address";
    public static final String COL_5 ="Phone";
    public static final String COL_6 ="Email_Address";
    public static final String COL_7 ="Course_of_Study";
    public static final String COL_8 ="Years_of_Graduation";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ( "
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " VARCHAR NOT NULL UNIQUE, "
                + COL_3+ " VARCHAR, "
                + COL_4 + " VARCHAR, "
                + COL_5+ " VARCHAR, "
                + COL_6 + " VARCHAR, "
                + COL_7+ " VARCHAR, "
                + COL_8+ " VARCHAR)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(SCS scs) {

        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, scs.getSCS_ID());
        contentValues.put(COL_2, scs.getName());
        contentValues.put(COL_3, scs.getNRIC());
        contentValues.put(COL_4, scs.getAddress());
        contentValues.put(COL_5, scs.getPhone());
        contentValues.put(COL_6, scs.getEmail_Address());
        contentValues.put(COL_7, scs.getCourse_of_Study());
        contentValues.put(COL_8, scs.getYears_of_Graduation());


        long id = database.insert(TABLE_NAME, null, contentValues);

        if(id != -1) {
            scs.setSCS_ID(""+id);
            Toast.makeText(context, "New event called " + scs.getName() + " successfully inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }

    public ArrayList<SCS> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<SCS> scss = new ArrayList<SCS>();
        SCS scs;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                scs = new SCS();
                scs.setSCS_ID(cursor.getString(0));
                scs.setName(cursor.getString(1));
                scs.setNRIC(cursor.getString(2));
               scs.setAddress(cursor.getString(3));
                scs.setPhone(cursor.getString(4));
                scs.setEmail_Address(cursor.getString(5));
                scs.setCourse_of_Study(cursor.getString(6));
                scs.setYears_of_Graduation(cursor.getString(7));

                scss.add(scs);
            }
        }
        cursor.close();
        database.close();

        return scss;
    }


    /**
     * This method to update user record
     *
     * @param
     */
    public boolean updateData(String Name, String NRIC, String Address,String Phone,String Email_Address,String Course_of_Study,String Years_of_Graduation) {

        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_2, Name);
            contentValues.put(COL_3, NRIC);
            contentValues.put(COL_4, Address);
            contentValues.put(COL_5, Phone);
            contentValues.put(COL_6, Email_Address);
            contentValues.put(COL_7, Course_of_Study);
            contentValues.put(COL_8, Years_of_Graduation);

            db.update(TABLE_NAME, contentValues, "Phone= ?", new String[]{Phone});
            db.update(TABLE_NAME, contentValues, "Course_of_Study= ?", new String[]{Course_of_Study});
            db.update(TABLE_NAME, contentValues, "Years_of_Graduation = ?", new String[]{Years_of_Graduation});
            return true;
        }
    }
    /**
     * This method is to delete user record
     *
     * @param
     */
    public boolean deleteUser(String Name ) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        SCS scs = new SCS();

        if (cursor.moveToFirst()) {
            scs.setSCS_ID(cursor.getString(0));
            db.delete(TABLE_NAME, COL_1 + " = ?",
                    new String[] { String.valueOf(scs.getSCS_ID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    /**
     * This method to check user exist or not
     *
     * @param
     * @return true/false
     */
    public SCS checkUser (String Name ) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Name + "\"";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        SCS   scs = new SCS();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            scs.setSCS_ID(cursor.getString(0));
            scs.setName(cursor.getString(1));
            scs.setNRIC(cursor.getString(2));
            scs.setAddress(cursor.getString(3));
            scs.setPhone(cursor.getString(4));
            scs.setEmail_Address(cursor.getString(5));
            scs.setCourse_of_Study(cursor.getString(6));
            scs.setYears_of_Graduation(cursor.getString(7));


            cursor.close();
        } else {
            scs  = null;
        }
        db.close();
        return  scs ;
    }




}





