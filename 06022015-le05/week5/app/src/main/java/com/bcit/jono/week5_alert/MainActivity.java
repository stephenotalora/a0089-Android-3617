package com.bcit.jono.week5_alert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends Activity implements Button.OnClickListener {
    Button alertButton;
    Button alertWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertButton = (Button)findViewById(R.id.alert_button);
        alertWidget = (Button)findViewById(R.id.alert_widget);
        alertButton.setOnClickListener(this);
        alertWidget.setOnClickListener(this);
    }

    private AlertDialog buildAlert(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_LONG).show();
            }
        });

        if (title != null && !title.isEmpty()) {
            builder.setTitle(title);
        }

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alert_button:
                buildAlert("MY ALERT MESSAGE GOES HERE!!!").show();
                break;
            case R.id.alert_widget:
                DatePicker datePicker = new DatePicker(this);
                Calendar calendar = Calendar.getInstance();
                datePicker.init(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE), new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //TODO: handle date change here
                            }
                        });
                AlertDialog dialog = buildAlert("Now with widget");
                dialog.setView(datePicker);
                dialog.show();
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
