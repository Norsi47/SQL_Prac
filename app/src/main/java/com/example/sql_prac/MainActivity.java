package com.example.sql_prac;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
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

    SearchView searchView;

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
        //will show created list as soon as app is opened
        showCustomerOnListView(dataBaseHelper);

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
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    //default value to match the one at the top of try
                    Toast.makeText(MainActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                //inserting customer
                boolean success = dataBaseHelper.addOne(customerModel);
                //should print out true or false in app
            // ON BTN_ADD
                Toast.makeText(MainActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();


        });

        btn_viewAll.setOnClickListener((v) -> {

            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //views all the customer in a neater form on screen compared to Toast
            // ON VIEW ALL
           showCustomerOnListView(dataBaseHelper);

                //same thing
//                Toast.makeText(MainActivity.this, customerModelList.toString(), Toast.LENGTH_LONG).show();

        });


        //listens when being clicked
        listViewCustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel customerModel = (CustomerModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(customerModel);
                showCustomerOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted " + customerModel.toString(), Toast.LENGTH_SHORT).show();
            }
        } );
    }


    //was refactored from the top , then extract method
    private void showCustomerOnListView (DataBaseHelper dataBaseHelper1) {
        arrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,
                  android.R.layout.simple_expandable_list_item_1, dataBaseHelper1.getAllCustomer());
        listViewCustomerList.setAdapter(arrayAdapter);
    }
}