package com.bcit.jono.intentservicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements Button.OnClickListener {
    private Button button;
    public static final String IMAGE_URL = "http://www.mcescher.com/Gallery/back-bmp/LW389.jpg";//"http://img3.wikia.nocookie.net/__cb20130524024810/logopedia/images/f/fa/Apple_logo_black.svg";
    public static final String IMAGE_DOWNLOAD_KEY = "IMAGE_DOWNLOAD_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.action_service);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ImageDownloaderService.class);
        intent.putExtra(IMAGE_DOWNLOAD_KEY, IMAGE_URL);
        startService(intent);
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
