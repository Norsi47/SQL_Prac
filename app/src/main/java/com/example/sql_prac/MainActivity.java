package com.example.sql_prac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//testing test
public class MainActivity extends AppCompatActivity {

    //reference to buttons and other layout
    Button btn_add, btn_viewAll;

    EditText editName, editAge;

    Switch aSwitchActiveCustomer;

    ListView listViewCustomerList;

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

        //this is the button click listener, logic to add and view all listeners
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //using try and catch to see if error comes up (similar to if else statement)
                //better option in this case, will prevent app from crashing

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
                Toast.makeText(MainActivity.this, "Success= " +success, Toast.LENGTH_LONG).show();
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //same thing
                Toast.makeText(MainActivity.this, "View all button", Toast.LENGTH_LONG).show();
            }
        });

    }
}