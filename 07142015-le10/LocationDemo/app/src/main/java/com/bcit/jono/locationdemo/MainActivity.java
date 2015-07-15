package com.bcit.jono.locationdemo;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity {
    private TextView textView;
    private Location currentLocation;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.action_data);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (currentLocation != null) {
            showCurrentLocation();
        }

        // register listener to get location update
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, listener);
    }

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("LocationDemo", String.format("Current Location: %f %f",
                    location.getLatitude(), location.getLongitude()
            ));
            currentLocation = location;
            showCurrentLocation();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void showCurrentLocation() {
        String msg = String.format("Current Location %f %f",
                currentLocation.getLatitude(), currentLocation.getLongitude()
        );
        textView.setText(msg);
        // a copy is needed here so that the threads don't update location at the same time
        Location locationCopy = new Location(currentLocation);
        (new GetAddressTassk()).execute(locationCopy);
    }

    @Override
    protected void onPause() {
        locationManager.removeUpdates(listener);
        super.onPause();
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


    private class GetAddressTassk extends AsyncTask<Location, Void, String> {
        private Exception exp;

        @Override
        protected String doInBackground(Location... params) {
            String addressText;
            Log.v("TEST", "Address begin update");
            Geocoder gc = new Geocoder(MainActivity.this, Locale.getDefault());
            Location location = params[0];
            List<Address> addresses = null;
            try {
                addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (Exception e) {
                exp = e;
                e.printStackTrace();
            }

            Log.v("TEST", "addresses = " + addresses);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                addressText = String.format("%s %s %s",
                        address.getAddressLine(0),
                        address.getLocality(),
                        address.getCountryName()
                );
            } else {
                addressText = "Unknown";
            }

            return addressText;
        }

        @Override
        protected void onPostExecute(String s) {
            if (exp == null) {
                textView.setText(s);
            } else {
                textView.setText("Some exception happened " + exp.getMessage());
            }
        }
    }
}
