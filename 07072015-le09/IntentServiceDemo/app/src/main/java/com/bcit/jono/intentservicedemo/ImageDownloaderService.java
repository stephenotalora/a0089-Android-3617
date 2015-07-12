package com.bcit.jono.intentservicedemo;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Display;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class ImageDownloaderService extends IntentService {

    public ImageDownloaderService() {
        super("ImageDownloaderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlToDownload = intent.getStringExtra(MainActivity.IMAGE_DOWNLOAD_KEY);
        HttpURLConnection conn = null;
        int random = new Random().nextInt();
        String downloadLocation = getCacheDir() + File.pathSeparator +String.format("%d.jpg", random);
        try {
            URL url = new URL(urlToDownload);
            conn = (HttpURLConnection)url.openConnection();
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            OutputStream outputStream = new FileOutputStream(downloadLocation);
            int ch = 0;
            while ((ch = inputStream.read()) != -1){
                outputStream.write(ch);
            }
            outputStream.close();
            showNotification(downloadLocation);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @SuppressWarnings("deprication")
    private void showNotification(String downloadLocation) {
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentText("Image Downloaded");
        builder.setSmallIcon(R.drawable.ic_stat_image_downloaded);
        builder.setAutoCancel(true);


        Intent intent = new Intent(this, DisplayImage.class);
        intent.putExtra("DOWNLOAD_IMAGE", downloadLocation);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        notificationManager.notify(100, builder.getNotification());
    }
}
