package com.example.sql_prac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        //writable data
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
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOne(CustomerModel customerModel) {
        /*if customerModel is found in database, delete it and then return true
         * if not found return false*/

        //writable because we delete from it
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //delete anyone with same id number
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE "
                + COLUMN_ID + " = " + customerModel.getId();
        //what does rawQuery mean?
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        //used alt enter to make "create local variable" for cursor
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

//    public void searchByUserName(CustomerModel customerModel) {
//        /* Go into customer model and search by user name */
//        //do I need to save the customer in an array list first?
//
//        //sql to select all names in customer table
//        String queryString = "SELECT " + COLUMN_CUSTOMER_NAME + " FROM " +
//                CUSTOMER_TABLE + " = " + customerModel.getName();
//
//        //reading what is in the database
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
//
//       //while loop
//        StringBuffer stringBuffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//            stringBuffer.append("ID" + cursor.getInt(0) + "\n");
//            stringBuffer.append("Name" + cursor.getString(1) + "\n");
//            stringBuffer.append("Age" + cursor.getInt(2) + "\n");
//
//        }
//        cursor.close();
//        sqLiteDatabase.close();
//
//
//    }


    //method to "SELECT all records from the table"
    public List<CustomerModel> getAllCustomer() {
        List<CustomerModel> customerModelList = new ArrayList<>();
        //SELECT DATA from database

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        //an empty list that gets readable data saved
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        //this will move to the first value if true
        if (cursor.moveToFirst()) {
            /*loop through result set if there are customers
             * create new customer objects
             * then return list*/
            do {
                //items from database
                //the numbers in () are the columns in the data base
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                //Ternary Operator, similar to if else statement
                boolean customerActive = cursor.getInt(3) == 1 ? true : false;

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge, customerActive);
                customerModelList.add(newCustomer);
                //move through database one at a time
            } while (cursor.moveToNext());

        } else {
            //failer, not to do anything to the list
        }
        //closes cursor  when dbd is done, so others can use it
        //sql lite does this
        cursor.close();
        sqLiteDatabase.close();
        return customerModelList;
    }

//now learn how to search by database
    //add search on UI
    //List will show only users that were selected and not the entire users
}
