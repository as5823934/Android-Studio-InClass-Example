package com.example.huntertsai.customadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView myList;
    private String[] listItem = {"Panda", "Turtle", "Fish", "Cat", "Dog", "Fish"};
    private int[] imageList = {R.drawable.panda, R.drawable.turtle, R.drawable.fish, R.drawable.cat, R.drawable.dog, R.drawable.bird};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList = findViewById(R.id.list_view);

        MyAdapter myAdapter = new MyAdapter(this, R.layout.activity_listview, listItem, imageList);
        myList.setAdapter(myAdapter);


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "You selected "+listItem[i], Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class MyAdapter extends ArrayAdapter<String>{
        private int layoutId;
        private String[] data;
        private int[] image;

        public MyAdapter(@NonNull Context context, int resource, @NonNull String[] list, int[] image) {
            super(context, resource, list);
            layoutId = resource;
            data = list;
            this.image = image;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent){
            //view = super.getView(i, view, parent);
            view = getLayoutInflater().inflate(layoutId, parent, false);
            TextView textView = view.findViewById(R.id.text_view);
            ImageView imageView = view.findViewById(R.id.image_view);
            textView.setText(listItem[i]);
            imageView.setImageResource(imageList[i]);

            return view;
        }
    }
}


