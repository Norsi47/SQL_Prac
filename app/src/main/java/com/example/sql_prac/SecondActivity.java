package com.example.sql_prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class SecondActivity extends AppCompatActivity {

    Button backButton;
    ArrayAdapter arrayAdapter;

    MainActivity mainActivity = new MainActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        configureBackButton();

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
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mainActivity);
        CustomerModel customerModel = new CustomerModel();

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
                dataBaseHelper.searchByUserName(customerModel);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }


}