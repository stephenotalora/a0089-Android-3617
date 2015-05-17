package com.example.jono.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ParentActivity {

    // Symbolic constants
    private final static String TAG = "Lab-ActivityOne";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Entered the onCreate() method");

        // init views
        mTextView1 = (TextView) findViewById(R.id.textView);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mIntentButton = (Button)findViewById(R.id.launchActivity);

        if (savedInstanceState != null) {
            // restore data
            mOnRestart = savedInstanceState.getInt(RESTART_KEY);
            mOnResume = savedInstanceState.getInt(RESUME_KEY);
            mOnStart = savedInstanceState.getInt(START_KEY);
            mOnCreateCount = savedInstanceState.getInt(CREATE_KEY);
        }

        ++mOnCreateCount;
        final Intent initActivtyTwo = new Intent(this, SecondaryActivity.class);
        mIntentButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initActivtyTwo);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(RESTART_KEY, mOnRestart);
        outState.putInt(RESUME_KEY, mOnResume);
        outState.putInt(START_KEY, mOnStart);
        outState.putInt(CREATE_KEY, mOnCreateCount);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Entered the onStart() method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Entered the onResume() method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Entered the onRestart() method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Entered the onPause() method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Entered the onStop() method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Entered the onDestroy() method");
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
