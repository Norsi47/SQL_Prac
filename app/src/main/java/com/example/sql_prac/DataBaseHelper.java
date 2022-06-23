package com.example.sql_prac;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Customer.db", null, 1);
    }

    //this is called the first time an database is accessed
    //it is called when the app inputs new data
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME +
                " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " +
                COLUMN_ACTIVE_CUSTOMER + " BOOL)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    //called if database version number changes
    //prevents previous user apps from breaking when changes are done in database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


    }

    public boolean addOne(CustomerModel customerModel) {
        //the .getWritable came from the extended class SQLOpenHelper
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*this stores data in pairs
         e.g contentValues.put("name", value)
          contentValues.getString("name")*/
        ContentValues contentValues = new ContentValues();
        //gets the Name and age from Customer Model class and then puts it in the column
        contentValues.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        contentValues.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        contentValues.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());
        //no need for id since it is automatically done in the database

        long insert = sqLiteDatabase.insert(CUSTOMER_TABLE, null, contentValues);
        //if statement for if positive number return true, negative false
        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }


    }

}
