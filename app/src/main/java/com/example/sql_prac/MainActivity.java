package com.example.sql_prac;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

//testing test
//implemented search view to add search 1
public class MainActivity extends AppCompatActivity {

    //comes up in code part in UI, so it is needed to be called here to be used (most times)
    //reference to buttons and other layout
    Button btn_add, btn_viewAll, nextButton;

    EditText editName, editAge;

    Switch aSwitchActiveCustomer;

    ListView listViewCustomerList;

    ArrayAdapter arrayAdapter;
    DataBaseHelper dataBaseHelper;


    //go back button method
    private void configureNextButton() {
        nextButton = (Button) findViewById(R.id.nextActivity);
        //listener for when button is pressed
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will switch to new activity (next page) when clicked
                // in the () we are stating to from the main class when app is ran
                //then followed my second activity.class
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

    }


    //this starts the application
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        //for next button pressed
        configureNextButton();

        //should add customer whenever button add is clicked
        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        editName = findViewById(R.id.edit_name);
        editAge = findViewById(R.id.edit_Age);
        aSwitchActiveCustomer = findViewById(R.id.switch_active);
        listViewCustomerList = findViewById(R.id.listView_cutomerList);
        //for search


        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //APP OPEN
        //will show created list as soon as app is opened
        //returning the method below
        showCustomerOnListView(dataBaseHelper);




        //this is the button click listener, logic to add and view all listeners
        //need to look up wat (v) -> does
        btn_add.setOnClickListener((v) -> {

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
        //adapterview dsiplays items in the adapter
        listViewCustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel customerModel = (CustomerModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(customerModel);
                showCustomerOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted " + customerModel.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    //was refactored from the top , then extract method
    private void showCustomerOnListView(DataBaseHelper dataBaseHelper1) {
        arrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1, dataBaseHelper1.getAllCustomer());
        listViewCustomerList.setAdapter(arrayAdapter);
    }
//shows in next page now

    //for search icon to show and work

//    public boolean onCreateOptionsMenu(Menu menu) {
//        //get the menu.xml file (purple color is the name)
//        getMenuInflater().inflate(R.menu.menu, menu);
//        //get the search icon id
//        MenuItem menuItem = menu.findItem(R.id.search_icon);
//
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //if user types anything in search
//                arrayAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//
//        return super.onCreateOptionsMenu(menu);
//
//    }


}