package com.bcit.jono.networking;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * THIS IS A REST SAMPLE FROM CLASS # 8.
 */
public class MainActivity extends Activity implements Button.OnClickListener {
    private EditText editTextView;
    private Button searchButton;
    private TextView textView;
    private static final String GOOGLE_AJAX_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextView = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);
        searchButton = (Button)findViewById(R.id.action_search);
        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String searchInput = editTextView.getText().toString();
        new SearchTask().execute(searchInput);
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

    // if you make class static you won't be able to use the outer class instance variables
    private class SearchTask extends AsyncTask<String, Void, String> {
        private Exception exception; // this is so that you can estash any exceptions being thrown by the thread

        @Override
        protected String doInBackground(String... params) {
            String searchInput = params[0];
            StringBuilder response = new StringBuilder();
            String results = "No Results...";

            try {
                String queryString = GOOGLE_AJAX_SEARCH_URL + "&q=" + URLEncoder.encode(searchInput, "UTF-8");
                URL url = new URL(queryString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader rdr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = null;
                    while ((line = rdr.readLine()) != null ) {
                        response.append(line);
                    }
                    rdr.close();
                } else {
                    // handle this secnario ..
                    response.append("Something went wrong!");
                }

                results = parseJSONSearchResult(response);
            } catch (Exception e) {
                exception = e;
            }

            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            if (exception == null) {
                textView.setText(s);
            } else {
                textView.setText("Exception : " + exception.getMessage());
            }
        }

        private String parseJSONSearchResult(StringBuilder stringBuilder) throws Exception {
            StringBuilder builder = new StringBuilder();

            JSONObject data = new JSONObject(stringBuilder.toString());
            JSONObject responseData = data.getJSONObject("responseData");
            JSONArray results = responseData.getJSONArray("results");

            for (int i=0; i < results.length(); ++i) {
                String content = results.getJSONObject(i).getString("content");
                String formattedContent = (i + 1) + ". " + content + "\n";
                builder.append(formattedContent);
            }

            return builder.toString();
        }
    }
}
