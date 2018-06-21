package com.example.huntertsai.radiogroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void radioClick(View view){
        ImageView imageView = findViewById(R.id.imageView);
//        boolean checked = ((RadioButton) view).isChecked();
//
//        switch(view.getId()){
//            case R.id.panda:
//                if(checked)
//                    imageView.setImageResource(R.drawable.image1);
//                break;
//            case R.id.turtle:{
//                if(checked)
//                imageView.setImageResource(R.drawable.image2);
//                break;
//            }
//            case R.id.fish:{
//                if(checked)
//                imageView.setImageResource(R.drawable.images3);
//                break;
//            }
//        }

        if(view.getId() == R.id.panda){
            imageView.setImageResource(R.drawable.image1);
        } else if(view.getId() == R.id.turtle){
            imageView.setImageResource(R.drawable.image2);
        } else{
            imageView.setImageResource(R.drawable.images3);
        }
    }
}
