package com.example.huntertsai.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView myList;
    private String[] listItem = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList = findViewById(R.id.list_view);
        ArrayAdapter<String>arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.text_view, listItem);
        myList.setAdapter(arrayAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "You selected "+listItem[i], Toast.LENGTH_SHORT).show();
            }
        });
    }


}

