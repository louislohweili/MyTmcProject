//package com.example.louisloh.mytmcproject2.Database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;
//
//import com.example.louisloh.mytmcproject2.Methods.StaffRegister;
//
//import java.util.ArrayList;
//
///**
// * Created by louisloh on 10/5/2017.
// */
//
//public class DatabaseHelperStaffR extends SQLiteOpenHelper {
//    private static final int DATABASE_VERSION = 1;
//
//    private SQLiteDatabase database;
//    private Context context;
//
//    public DatabaseHelperStaffR(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
//    }
//    public static final String DATABASE_NAME ="StaffLogin.db";
//    public static final String TABLE_NAME ="StaffLogin_table";
//    public static final String COL_1 ="Staff_id";
//    public static final String COL_2 ="Staff_Name";
//    public static final String COL_3 ="Staff_Email";
//    public static final String COL_4 ="Staff_Password";
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        db.execSQL("create table " + TABLE_NAME + " ( "
//                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + COL_2 + " VARCHAR NOT NULL UNIQUE , "
//                + COL_3+ " VARCHAR, "
//                + COL_4+ " VARCHAR)");
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    public void insertRecord(StaffRegister staffregister) {
//
//        database = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, staffregister.getStaff_id());
//        contentValues.put(COL_2, staffregister.getStaff_Name());
//        contentValues.put(COL_3, staffregister.getStaff_Email());
//        contentValues.put(COL_4, staffregister.getStaff_Password());
//
//        long id = database.insert(TABLE_NAME, null, contentValues);
//
//        if(id != -1) {
//            staffregister.setStaff_id(""+id);
//            Toast.makeText(context, "New event called " + staffregister.getStaff_Name() + " successfully inserted", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
//        }
//
//        database.close();
//    }
//
//    public ArrayList<StaffRegister> getAllRecords() {
//        database = this.getReadableDatabase();
//        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);
//
//        ArrayList<StaffRegister> staffRegisters = new ArrayList<StaffRegister>();
//        StaffRegister staffRegister;
//        if (cursor.getCount() > 0) {
//            for (int i = 0; i < cursor.getCount(); i++) {
//                cursor.moveToNext();
//
//                staffRegister = new StaffRegister();
//                staffRegister.setStaff_id(cursor.getString(0));
//                staffRegister.setStaff_Name(cursor.getString(1));
//                staffRegister.setStaff_Email(cursor.getString(2));
//                staffRegister.setStaff_Password(cursor.getString(3));
//
//
//                staffRegisters.add(staffRegister);
//            }
//        }
//        cursor.close();
//        database.close();
//
//        return staffRegisters;
//    }
//
//
//    /**
//     * This method to update user record
//     *
//     * @param
//     */
//    public boolean updateData (String Staff_Password)
//
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//
//        contentValues.put(COL_4,Staff_Password);
//
//        db.update(TABLE_NAME, contentValues, "Staff_Password = ?",new String[] { Staff_Password });
//        return true;
//    }
//
//    /**
//     * This method is to delete user record
//     *
//     * @param
//     */
//    public boolean deleteUser(String  Staff_Name) {
//        boolean result = false;
//
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Staff_Name + "\"";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        StaffRegister staffRegister = new StaffRegister();
//
//        if (cursor.moveToFirst()) {
//            staffRegister.setStaff_id(cursor.getString(0));
//            db.delete(TABLE_NAME, COL_1 + " = ?",
//                    new String[] { String.valueOf(staffRegister.getStaff_id()) });
//            cursor.close();
//            result = true;
//        }
//        db.close();
//        return result;
//    }
//
//    /**
//     * This method to check user exist or not
//     *
//     * @param
//     * @return true/false
//     */
//    public StaffRegister checkStaffUser (String Staff_Name ) {
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  \"" + Staff_Name + "\"";
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        StaffRegister    staffRegister = new   StaffRegister();
//
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            staffRegister .setStaff_id(cursor.getString(0));
//            staffRegister .setStaff_Name(cursor.getString(1));
//            staffRegister .setStaff_Email(cursor.getString(2));
//            staffRegister .setStaff_Password(cursor.getString(3));
//
//
//            cursor.close();
//        } else {
//            staffRegister  = null;
//        }
//        db.close();
//        return  staffRegister ;
//    }
//
//    /**
//     * This method to check user exist or not
//     *
//     * @param Name
//     * @param password
//     * @return true/false
//     */
//    public boolean checkUser(String Name, String password) {
//
//        // array of columns to fetch
//        String[] columns = {
//                COL_1
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        // selection criteria
//        String selection = COL_2 + " = ?" + " AND " + COL_4 + " = ?";
//
//        // selection arguments
//        String[] selectionArgs = {Name, password};
//
//        // query user table with conditionsf
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
//         */
//        Cursor cursor = db.query(TABLE_NAME, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);                      //The sort order
//
//        int cursorCount = cursor.getCount();
//
//        cursor.close();
//        db.close();
//        return cursorCount > 0;
//
//    }
//
//
//
//}
//
//
//
//
//
