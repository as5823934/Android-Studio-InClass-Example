package com.example.huntertsai.namequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private TextView first_view;
    private ListView lv;
    private List<String>options;
    private Map<String, String> name;
    private TextView secret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = new HashMap<>();
        lv = findViewById(R.id.nameList);
        secret = findViewById(R.id.extra);

        Intent intent_menu = getIntent();
        secret.setText(intent_menu.getStringExtra("secret"));

        readFile();
        getQuestion();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_lastname = adapterView.getItemAtPosition(i).toString();
                String fisrtname = first_view.getText().toString();
                if(selected_lastname.equalsIgnoreCase(name.get(fisrtname))){
                    Toast.makeText(getBaseContext(), "Right", Toast.LENGTH_SHORT).show();
                    getQuestion();
                } else{
                    Toast.makeText(getBaseContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void readFile() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.names));

        while (scan.hasNext()){
            String line = scan.nextLine();
            String[] splitted = line.split("\t");
            name.put(splitted[0], splitted[1]);
        }
        try{
            Scanner scan2 = new Scanner(openFileInput("new_name.txt"));

            while (scan2.hasNext()) {
                String line = scan2.nextLine();
                String[] splitted = line.split("\t");
                name.put(splitted[0], splitted[1]);
            }
            scan2.close();
        }catch(FileNotFoundException e){

        }

        scan.close();

    }

    private void getQuestion() {
        ArrayList<String> firstnames = new ArrayList<>(name.keySet());

        Random rand = new Random();
        int q_index = rand.nextInt(firstnames.size());

        String question = firstnames.get(q_index);
        first_view = findViewById(R.id.first_name);
        first_view.setText(question);

        ArrayList<String> lastnames = new ArrayList<>(name.values());
        String answer = name.get(question);
        lastnames.remove(answer);
        options = lastnames.subList(0,4);
        options.add(answer);
        Collections.shuffle(options);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        lv.setAdapter(adapter);

    }


    public void back(View view) {

        finish();
    }
}
