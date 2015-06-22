package com.bcit.jono.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {
    private Spinner spinner;
    private AutoCompleteTextView autoCompleteTextView;

    private static final String[] nhlTeams = {
            "Anaheim Ducks",
            "Arizona Coyotes",
            "Boston Bruins",
            "Buffalo Sabres",
            "Calgary Flames",
            "Carolina Hurricanes",
            "Chicago Blackhawks",
            "Colorado Avalanche",
            "Columbus Blue Jackets",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.spinner);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autocomplete);

        String [] teams = getResources().getStringArray(R.array.nhlItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teams); //nhlTeams);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = nhlTeams[position];
                Toast.makeText(MainActivity.this, "Selected Item" + selectedItem, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teams);
        autoCompleteTextView.setAdapter(adapter2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
