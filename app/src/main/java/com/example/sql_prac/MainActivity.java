package com.example.sql_prac;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

//testing test
public class MainActivity extends AppCompatActivity {

    //reference to buttons and other layout
    Button btn_add, btn_viewAll;

    EditText editName, editAge;

    Switch aSwitchActiveCustomer;

    ListView listViewCustomerList;

    ArrayAdapter arrayAdapter;
    DataBaseHelper dataBaseHelper;

    //this starts the application
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        //should add customer whenever button add is clicked
        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        editName = findViewById(R.id.edit_name);
        editAge = findViewById(R.id.edit_Age);
        aSwitchActiveCustomer = findViewById(R.id.switch_active);
        listViewCustomerList = findViewById(R.id.listView_cutomerList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //APP OPEN
        //will create list as soon as app is opened
      arrayAdapter = new ArrayAdapter <CustomerModel>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1, dataBaseHelper.getAllCustomer());
      listViewCustomerList.setAdapter(arrayAdapter);

        //this is the button click listener, logic to add and view all listeners
        //need to look up wat (v) -> does
        btn_add.setOnClickListener((v) ->{

                CustomerModel customerModel;
                try {
                    //numbers put in here are for test
                    customerModel = new CustomerModel(-1, editName.getText().toString(), Integer.parseInt(editAge.getText().toString()),
                            aSwitchActiveCustomer.isChecked());
                    //to test if button works
                    //this shows up when button is pressed in app
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    //default value to match the one at the top of try
                    customerModel = new CustomerModel(-1, "error", 0, false);
                    Toast.makeText(MainActivity.this, "error creating customer", Toast.LENGTH_LONG).show();

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                //inserting customer
                boolean success = dataBaseHelper.addOne(customerModel);
                //should print out true or false in app
//                Toast.makeText(MainActivity.this, "Success= " +success, Toast.LENGTH_LONG).show();
            // ON BTN_ADD
            arrayAdapter = new ArrayAdapter <CustomerModel>(MainActivity.this,
                    android.R.layout.simple_expandable_list_item_1, dataBaseHelper.getAllCustomer());
            listViewCustomerList.setAdapter(arrayAdapter);
        });

        btn_viewAll.setOnClickListener((v) -> {

            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //views all the customer in a neater form on screen compared to Toast
            // ON VIEW ALL
            arrayAdapter = new ArrayAdapter <CustomerModel>(MainActivity.this,
                    android.R.layout.simple_expandable_list_item_1, dataBaseHelper.getAllCustomer());
            listViewCustomerList.setAdapter(arrayAdapter);

                //same thing
//                Toast.makeText(MainActivity.this, customerModelList.toString(), Toast.LENGTH_LONG).show();

        });

    }
}