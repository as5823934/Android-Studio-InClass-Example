package com.example.huntertsai.restful_api;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    private String joke_url = "http://api.icndb.com/jokes/random";
    private String cat_url = "http://thecatapi.com/api/images/get?format=xml&results_per_page=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_joke);
        imageView = findViewById(R.id.iv_photo);
    }

    public void get_jokes(View view) {

        Ion.with(this)
                .load(joke_url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = new JSONObject(result);
                            String joke = json.getJSONObject("value").getString("joke");
                            textView.setText(joke);
                        } catch (JSONException e1) {
                            Log.e("Mainactivity", "onCompleted: " + e1.getLocalizedMessage());
                        }
                    }
                });

    }

    public void get_photo(View view) {
        Ion.with(this)
                .load(cat_url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = XML.toJSONObject(result);
                            JSONArray img = json.getJSONObject("response")
                                    .getJSONObject("data")
                                    .getJSONObject("images")
                                    .getJSONArray("image");

                            String url = img.getJSONObject(0).getString("url");
                            System.out.println(url);
                            Picasso.with(MainActivity.this).load(url).resize(imageView.getWidth(), imageView.getHeight()).into(imageView);

                        } catch (JSONException e1) {
                            Log.e("Mainactivity", "onCompleted: " + e1.getLocalizedMessage());
                        }

                    }
                });


    }
}
