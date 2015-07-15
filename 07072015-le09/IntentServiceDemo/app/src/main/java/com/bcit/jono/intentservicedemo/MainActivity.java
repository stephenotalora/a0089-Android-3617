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
    public static final String VIDEO_URL = "https://r2---sn-ni5f-t8ge.googlevideo.com/videoplayback?expire=1436810088&ip=70.71.183.245&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&ratebypass=yes&sver=3&mv=m&id=o-AA8ZjqKmu0MWS9ahL89EPwsJuA04UBZbKOu3Ib9Ez0vV&mm=31&mn=sn-ni5f-t8ge&mt=1436788359&pl=17&itag=22&ms=au&upn=BQJ9KZ3pmgo&source=youtube&signature=476A8F34F1A8AF89F9E9875ACA9F3F997AB6847D.AF22F43E0FA99AFFEF0223373D54E656513334F0&requiressl=yes&mime=video%2Fmp4&initcwndbps=1947500&ipbits=0&fexp=901803%2C901816%2C9406006%2C9406819%2C9408142%2C9408420%2C9408710%2C9412755%2C9412774%2C9412913%2C9415429%2C9416126%2C9416729%2C9416768%2C9417094%2C9417121%2C9417292&lmt=1398168362477036&key=yt5&dur=90.139&signature=476A8F34F1A8AF89F9E9875ACA9F3F997AB6847D.AF22F43E0FA99AFFEF0223373D54E656513334F0";
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
        intent.putExtra(IMAGE_DOWNLOAD_KEY, VIDEO_URL);
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
