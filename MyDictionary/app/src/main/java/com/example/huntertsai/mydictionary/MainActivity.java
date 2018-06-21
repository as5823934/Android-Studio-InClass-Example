package com.example.huntertsai.mydictionary;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



public class MainActivity extends AppCompatActivity {

    private String language = "en";
    private String word = "";
    private String word_id;
    private String entriesUrl = "https://od-api.oxforddictionaries.com:443/api/v1/entries/";
    private String audioUrl = "";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private static final int REQ_CODE_BOOKMARK = 1111;

    private TextView definitionView;
    private TextView wordView;
    private TextView lexicalCategoryView;
    private TextView etymologiesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new CallbackTask().execute(dictionaryEntries());
        //langSelect();


    }

    private String dictionaryEntries() {
        word_id = word.toLowerCase();
        return entriesUrl + language + "/" + word_id;
    }

//search button
    public void searchClicked(View view) {
        try {
            SearchView searchView = findViewById(R.id.search_view);
            word = searchView.getQuery().toString();

            if (word.equals("")){
                Toast.makeText(MainActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
            }else{
                new CallbackTask().execute(dictionaryEntries());
            }
            
        } catch (Exception ioe) {

        }
        //animation for button
        Button searchBtn = findViewById(R.id.search);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        searchBtn.startAnimation(myAnim);
    }
//to book mark page
    public void toBookMark(View view) {
        Intent intent = new Intent(this, BookMarkActivity.class);
        startActivityForResult(intent, REQ_CODE_BOOKMARK );

    }

//add word and write into file
    public void addWord(View view) {
        if (word.equals("")){
            Toast.makeText(this, "Field is empty", Toast.LENGTH_SHORT).show();
        }else{
            String list_word = ((TextView)findViewById(R.id.word)).getText().toString();
            String list_def = ((TextView)findViewById(R.id.definition)).getText().toString();
            String list_lex = ((TextView)findViewById(R.id.lexicalCategory)).getText().toString();
            String list_etymo = ((TextView)findViewById(R.id.etymologies)).getText().toString();

            String put = list_word + "\t" + list_lex + "\t" + list_etymo + "\t" + list_def;

            try{
                PrintStream output = new PrintStream(openFileOutput("bookmark.txt", MODE_APPEND | MODE_PRIVATE));
                output.println(put);
                output.close();

            } catch (IOException e){
            }

            Toast.makeText(getBaseContext(), word + " is added", Toast.LENGTH_SHORT).show();
            System.out.println(put);
        }
//animation for button
        Button addBtn = findViewById(R.id.saveWord);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        addBtn.startAnimation(myAnim);


    }

    public void search_img(View view) {
        if (word.equals("")){
            Toast.makeText(this, "Field is empty", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.bing.com/images/search?q="+word+"&qs=n&form=QBIR&sp=-1&pq=hello&sc=8-5&sk=&cvid=6625892FD27F490D8FB9BD582D85D107"));
            startActivity(intent);
        }
//animation for button
        Button imageBtn = findViewById(R.id.word_image);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        imageBtn.startAnimation(myAnim);

    }

//play audio url
    public void playAudio(View view) throws IOException {

        if (word.equals("") || audioUrl.equals("")){
            Toast.makeText(MainActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
        }else{
            Uri myUri = Uri.parse(audioUrl);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(this, myUri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare(); //don't use prepareAsync for mp3 playback
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//animation for button
        Button playBtn = findViewById(R.id.audio);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        playBtn.startAnimation(myAnim);
    }

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
            System.out.println(result);
            Answer(result);

        }
    }

    // read json data and set textview
    private void Answer(String result) {
        definitionView = findViewById(R.id.definition);
        wordView = findViewById(R.id.word);
        lexicalCategoryView = findViewById(R.id.lexicalCategory);
        etymologiesView = findViewById(R.id.etymologies);

        try {
            JSONObject json = new JSONObject(result);
            JSONArray results = json.getJSONArray("results");
            //System.out.println(results);

            JSONObject jsonObject = results.getJSONObject(0);
            String id = jsonObject.getString("id");
            System.out.println("Word: " + id);
            wordView.setText(id.toUpperCase());

            JSONArray lexicalEntries = jsonObject.getJSONArray("lexicalEntries");
            //System.out.println(lexicalEntries);

            JSONObject jsonObject1 = lexicalEntries.getJSONObject(0);
            JSONArray entries = jsonObject1.getJSONArray("entries");
            //System.out.println(entries);

            String lexicalCategory = jsonObject1.getString("lexicalCategory");
            System.out.println("LexicalCategory: " + lexicalCategory);
            lexicalCategoryView.setText(lexicalCategory);


            JSONObject jsonObject2 = entries.getJSONObject(0);
            JSONArray etymologies = jsonObject2.getJSONArray("etymologies");
            System.out.println("Etymologies: " + etymologies.get(0));
            etymologiesView.setText((CharSequence) etymologies.get(0));

            JSONArray senses = jsonObject2.getJSONArray("senses");
            //System.out.println(senses);



            JSONObject jsonObject3 = senses.getJSONObject(0);
            JSONArray definitions = jsonObject3.getJSONArray("definitions");
            System.out.println("Definitions: " + definitions.get(0));
            definitionView.setText((CharSequence) definitions.get(0));

            JSONArray domains = jsonObject3.getJSONArray("domains");
            System.out.println("Domains: " + domains.get(0));

            JSONArray pronunciations = jsonObject1.getJSONArray("pronunciations");
            JSONObject jsonObject4 = pronunciations.getJSONObject(0);
            String audioFile = jsonObject4.getString("audioFile");
            System.out.println("Audio: " + audioFile);
            audioUrl = audioFile;

        } catch (JSONException e) {

        }
    }

    private void langSelect() {
        Spinner langSpin = findViewById(R.id.langSpin);
        langSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String langSelected = (String) langSpin.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }



}
