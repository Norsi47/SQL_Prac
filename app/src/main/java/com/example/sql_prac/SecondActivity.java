package com.example.sql_prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Button backButton;
    ArrayAdapter arrayAdapter;
    DataBaseHelper dataBaseHelper;
    //to show the list
    ListView listView;

    MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        configureBackButton();

        //setting list view to id
        listView = (ListView) findViewById(R.id.showList);

//        mainActivity.showCustomerOnListView(dataBaseHelper);
        //arrayAdapter needs to be an array list
//        arrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_expandable_list_item_1, dataBaseHelper.getAllCustomer());
//        listView.setAdapter(arrayAdapter);


    }

    public void showCustomerOnListView(DataBaseHelper dataBaseHelper1) {
        arrayAdapter = new ArrayAdapter<CustomerModel>(SecondActivity.this,
                android.R.layout.simple_expandable_list_item_1, dataBaseHelper1.getAllCustomer());
        listView.setAdapter(arrayAdapter);
    }

    //for main menu button
    private void configureBackButton() {
        backButton = (Button) findViewById(R.id.goBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will take back to mainactivity (main menu)
                finish();

            }
        });

    }

    //for search icon to show and work

    public boolean onCreateOptionsMenu(Menu menu) {
        //get the menu.xml file (purple color is the name)
        getMenuInflater().inflate(R.menu.menu, menu);
        //get the search icon id
        MenuItem menuItem = menu.findItem(R.id.search_icon);


//        mainActivity.showCustomerOnListView(dataBaseHelper);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //if user types anything in search
               arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }


}