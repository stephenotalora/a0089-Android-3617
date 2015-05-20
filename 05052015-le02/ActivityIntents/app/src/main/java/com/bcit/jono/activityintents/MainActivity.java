package com.bcit.jono.activityintents;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements Button.OnClickListener {
    private TextView answerView;
    private Button addButton, mulButton;

    // symbolic constants
    private static final int ADD_OPERATION = 1000, MUL_OPERATION = 2000;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerView = (TextView) findViewById(R.id.textView);
        addButton = (Button) findViewById(R.id.addButton);
        mulButton = (Button) findViewById(R.id.mulButton);

        addButton.setOnClickListener(this);
        mulButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CalcActivity.class);

        switch (v.getId()){
            case R.id.addButton:
                intent.putExtra("a", 50); intent.putExtra("b", 100);
                intent.putExtra("requestCode", ADD_OPERATION);
                startActivityForResult(intent, ADD_OPERATION);
                break;
            case R.id.mulButton:
                intent.putExtra("c", 25); intent.putExtra("d", 35);
                intent.putExtra("requestCode", MUL_OPERATION);
                startActivityForResult(intent, MUL_OPERATION);
                break;
            default:
                Log.e(TAG, "Oopps! Button not found!");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            int result = data.getIntExtra("result", 0);
            String sResult = "the answer is: " + result;
            Toast.makeText(this, sResult, Toast.LENGTH_LONG).show();
            answerView.setText(sResult);
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
