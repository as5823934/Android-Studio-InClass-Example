package com.example.huntertsai.mydictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

public class BookMarkActivity extends AppCompatActivity {
    private static final int REQ_CODE_DEF = 111;
    private ListView listView;
    private Map<String,String> book;
    private Map<String,String> book_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark);
        listView = findViewById(R.id.bookList);

        readFile();
        loadList();

    }

    public void Back(View view) {
        Intent backToHomeActivityIntent = new Intent(this,MainActivity.class);
        backToHomeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(backToHomeActivityIntent);
    }

    //read file and put into hash map
    protected void readFile() {

        book = new HashMap<>();
        book_2 = new HashMap<>();
        try{
            Scanner scan = new Scanner(openFileInput("bookmark.txt"));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.split("\t").length >= 4) {
                    String[] splitted = line.split("\t");
                    book.put(splitted[0].toUpperCase(), splitted[1]);
                    book_2.put(splitted[2], splitted[3]);

                } else {
                    continue;
                }
            }
            scan.close();

        }catch(FileNotFoundException e){

        }

    }

//load list and put into definition activity
    private void loadList(){

        ArrayList<String> words = new ArrayList<>(book.keySet());
        ArrayList<String> lexs = new ArrayList<>(book.values());
        ArrayList<String> etymos = new ArrayList<>(book_2.keySet());
        ArrayList<String> defs = new ArrayList<>(book_2.values());

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, words);
//        listView.setAdapter(adapter);
        CustomListAdapter adapter = new CustomListAdapter(this, words, R.drawable.pin);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(BookMarkActivity.this, DefinitionActivity.class);
                intent.putExtra("word", words.get(i));
                intent.putExtra("lex", lexs.get(i));
                intent.putExtra("etymo", etymos.get(i));
                intent.putExtra("def", defs.get(i));

                startActivityForResult(intent, REQ_CODE_DEF);

            }
        });
    }

    public Map<String, String> getBook() {
        return book;
    }
}
