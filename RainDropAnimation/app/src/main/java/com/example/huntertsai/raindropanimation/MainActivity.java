package com.example.huntertsai.raindropanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Thread thread;
    private AnimationHelper animationHelper;
    Rain rain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RainAnimation rainAnimation = findViewById(R.id.rainAnimation);
        animationHelper = new AnimationHelper(rainAnimation, 50);
    }

    @Override
    protected void onPause() {
        super.onPause();
        thread.stop();
    }


    public void start_Rain(View view) {
//        final RainAnimation rainAnimation = findViewById(R.id.rainAnimation);
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                rainAnimation.runAnimation();
//            }
//        });
//        thread.start();
//        rainAnimation.postInvalidate();
        animationHelper.start();

    }

    public void stop_Rain(View view) {
        animationHelper.stop();
    }
}
