package com.example.huntertsai.fragmentsmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.iv);
        textView = findViewById(R.id.tv);

        Intent intent_data = getIntent();

        imageView.setImageResource(intent_data.getIntExtra("image", 0));
        textView.setText(intent_data.getStringExtra("text"));

    }
}
