package com.bcit.jono.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Timer;


public class MainActivity extends Activity implements Button.OnClickListener {
    private Button b1, b2;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.buttonView1);
        b2 = (Button)findViewById(R.id.buttonView2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        Log.v(TAG, String.format("String ID is: %d", android.os.Process.myTid()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonView1:
//                Intent intent = new Intent(this, BasicService.class);
//                intent.putExtra("a", 1);
//                intent.putExtra("b", 2);
                startService(new Intent(this, TimerService.class));
                break;
            case R.id.buttonView2:
                stopService(new Intent(this, TimerService.class));
                //stopService(new Intent(this, BasicService.class));
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
