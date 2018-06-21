package com.example.huntertsai.dynamicui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int imageSource [] = {R.drawable.brazil, R.drawable.canada, R.drawable.china, R.drawable.dominican_republic,
    R.drawable.flag_of_peru, R.drawable.japan, R.drawable.mexico, R.drawable.taiwan, R.drawable.turkey};

    String contryText[] = {"BRAZIL", "CANADA", "CHINA", "DOM_RE", "PERU", "JAPAN", "MEXICO", "TAIWAN", "TURKEY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.grid_main);

        for (int i = 0; i < contryText.length; i++){
            View countryBox = getLayoutInflater().inflate(R.layout.flag_layout, null);
            ImageView image = countryBox.findViewById(R.id.imageC);
            TextView text = countryBox.findViewById(R.id.textC);
            image.setImageResource(imageSource[i]);
            text.setText(contryText[i]);
            gridLayout.addView(countryBox);

        }
    }
}
