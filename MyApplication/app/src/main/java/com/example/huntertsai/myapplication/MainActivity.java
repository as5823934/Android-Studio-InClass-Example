package com.example.huntertsai.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int [] image = {R.drawable.image1, R.drawable.image2, R.drawable.images3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change();

    }

    private void change() {
        final Spinner spin = findViewById(R.id.myspin);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView result = findViewById(R.id.mytext);
                ImageView imageView = findViewById(R.id.myimage);

                String itemSelected = (String) spin.getSelectedItem();
                result.setText("This is " + itemSelected);

                String[] choices = getResources().getStringArray(R.array.mySpinArray);

                for( i = 0; i < image.length; i++){
                    if(itemSelected.equals(choices[i])){
                        imageView.setImageResource(image[i]);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
