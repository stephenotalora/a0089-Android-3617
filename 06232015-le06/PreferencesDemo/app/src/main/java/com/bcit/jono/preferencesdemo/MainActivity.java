package com.bcit.jono.preferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements Button.OnClickListener {
    private Button b1, b2;
    public static final String PREF_NAME = "SAMPLE_PREFS";
    public static final String USER_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.action_button1);
        b2 = (Button)findViewById(R.id.action_button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        switch (v.getId()) {
            case R.id.action_button1:
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(USER_NAME, "jdow");
                editor.apply();
                break;
            case R.id.action_button2:
                Toast.makeText(this, "Username is " + settings.getString(USER_NAME, "unknown"), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
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
