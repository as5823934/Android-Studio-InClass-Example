package com.example.huntertsai.dictionary_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String def;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CallbackTask().execute(dictionaryEntries());
        textView = findViewById(R.id.text_view);
    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = "Ace";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }


    //in android calling network requests on the main thread forbidden by default
    //create class to do async job
    private class CallbackTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            //TODO: replace with your own app id and app key
            final String app_id = "5a936133";
            final String app_key = "f922b2de72497087bfcd14da5fc2b1f1";
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("app_id", app_id);
                urlConnection.setRequestProperty("app_key", app_key);

                // read the output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                return stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject js = new JSONObject(result);
                JSONArray results = js.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject lentries = results.getJSONObject(i);
                    JSONArray la = lentries.getJSONArray("lexicalEntries");
                    for (int j = 0; j < la.length(); j++) {
                        JSONObject entries = la.getJSONObject(j);
                        JSONArray e = entries.getJSONArray("entries");
                        for (int i1 = 0; i1 < e.length(); i1++) {
                            JSONObject senses = la.getJSONObject(i1);
                            JSONArray s = entries.getJSONArray("senses");
                            JSONObject d = s.getJSONObject(0);
                            JSONArray de = d.getJSONArray("definitions");
                            def = de.getString(0);

                        }
                    }
                }
                Log.e("def", def);
                textView.setText(def);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
