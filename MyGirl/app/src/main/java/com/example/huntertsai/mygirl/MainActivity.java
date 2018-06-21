package com.example.huntertsai.mygirl;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Animation animFadeIn;
    private Animation animFadeOut;
    final Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.my_text);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);





        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mySentence("For you");
            }
        }, 2000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               mySentence("Turina");
            }
        }, 5000);


    }

    public void mySentence(final String myText){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(myText);
                textView.startAnimation(animFadeOut);

            }
        }, 0);

    }
}
