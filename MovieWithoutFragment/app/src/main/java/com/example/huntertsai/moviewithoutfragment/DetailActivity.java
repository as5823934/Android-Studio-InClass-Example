package com.example.huntertsai.moviewithoutfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Scanner;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTxtContent();
    }

    private void setTxtContent() {
        String title = getIntent().getStringExtra("title");

        int img_res_id = getResources().getIdentifier(title, "drawable", getPackageName());
        int txt_res_id = getResources().getIdentifier(title, "raw", getPackageName());

        ImageView imageView;
        TextView textView;
        imageView = findViewById(R.id.detail_img);
        imageView.setImageResource(img_res_id);

        Scanner scan = new Scanner(getResources().openRawResource(txt_res_id));
        String allText = "";
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            allText = line;
        }

        textView = findViewById(R.id.detail_text);
        textView.setText(allText);
        scan.close();
    }

}
