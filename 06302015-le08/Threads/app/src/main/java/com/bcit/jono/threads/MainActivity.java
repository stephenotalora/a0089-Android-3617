package com.bcit.jono.threads;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements Button.OnClickListener {
    private Button actionButton, secondButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButton = (Button)findViewById(R.id.action_button);
        secondButton = (Button)findViewById(R.id.second_button);
        actionButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Button on click.", Toast.LENGTH_LONG).show();
        // using threads
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                Log.v(MainActivity.class.getName(), "Thread is executing ....");
//                long result = fib(45);
//                Log.d(MainActivity.class.getName(), "result is " + result);
//
//                // MainActivity.this.runOnUiThread to return the result back to the UI thread
//            }
//        };
//        thread.start();

        // using Android AsyncTask
        if (v.getId() == R.id.second_button) {
            (new FibAsyncTask()).execute(45);
        }
    }

    private static long fib(int n){
        if (n <= 1){
            return 1;
        } else {
            return fib(n-2) + fib(n-1);
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

    private class FibAsyncTask extends AsyncTask<Integer, String, Long> {
        @Override
        protected Long doInBackground(Integer... params) {
            long result = 0;
            int input = params[0].intValue();
            for (int i = 0; i < input; ++i) {
                result = fib(i);
                String msg = String.format("Fib(%d) = %d", i, result);
                //onProgressUpdate(msg);
                publishProgress(msg); // this will publish back on UI thread
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            Toast.makeText(MainActivity.this, "The final result is " + aLong, Toast.LENGTH_LONG).show();
        }
    }
}
