package com.example.sql_prac;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.Data;

import com.example.sql_prac.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

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
                try {
                    //numbers put in here are for test
                    CustomerModel customerModel = new CustomerModel(-1, editName.getText().toString(), Integer.parseInt(editAge.getText().toString()),
                            aSwitchActiveCustomer.isChecked());
                    //to test if button works
                    Toast.makeText(MainActivity.this, customerModel.toString() , Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "error creating customer" , Toast.LENGTH_LONG).show();
                }



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