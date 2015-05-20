package com.bcit.jono.activityintents;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivity extends Activity {
    private TextView dataView;
    private Button calcButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        dataView = (TextView)findViewById(R.id.data);
        calcButtonView = (Button)findViewById(R.id.calcButton);

        int result = 0;
        Intent mainIntent = getIntent();
        if (mainIntent != null) {
            int a, b;
            int code = mainIntent.getIntExtra("requestCode", 0);
            if (code == 1000) {
                a = mainIntent.getIntExtra("a", 0);
                b = mainIntent.getIntExtra("b", 0);
                dataView.setText(a + " + " + b);
                result = a + b;
            } else {
                a = mainIntent.getIntExtra("c", 0);
                b = mainIntent.getIntExtra("d", 0);
                dataView.setText(a + " * " + b);
                result = a * b;
            }
        }

        final Intent intentResult = new Intent();
        intentResult.putExtra("result", result);
        calcButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, intentResult);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
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
