package com.example.zhangm0123.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2018.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_ADDRESS_CONTACT = "address";
    public static final String COLUMN_PHONE_CONTACT = "number";
    public static final String TABLE_ADDRESS = "Contact2018_address";
    public static final String TABLE_PHONE = "Contact2018_number";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase(); //for initial test of db creation
        Log.d("MyContactApp", "Databasehelper: constructed Databasehelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "Databasehelper: creating Databasehelper");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "Databasehelper: upgrading Databasehelper");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertData(String name, String address, String number){
        Log.d("MyContactApp", "Databasehelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(COLUMN_NAME_CONTACT, name);
        contentValue.put(COLUMN_ADDRESS_CONTACT, address);
        contentValue.put(COLUMN_PHONE_CONTACT, number);


        long result = db.insert(TABLE_NAME, null, contentValue);
        if(result == -1){
            Log.d("MyContactApp", "Databasehelper: Contact insert - FAILED");
            return false;
        }
        else{
            Log.d("MyContactApp", "Databasehelper: Contact insert - PASSED");
            return true;
        }
    }


}
