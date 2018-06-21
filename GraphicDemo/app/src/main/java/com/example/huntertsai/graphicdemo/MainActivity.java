package com.example.huntertsai.graphicdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private  Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        thread.stop();
    }

    public void startAnimation(View view) {
        final AnimateView av = findViewById(R.id.myAnimation);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                av.doAnimation();
            }
        });
           thread.start();

        av.postInvalidate();

    }

    public void stopAnimation(View view) {

    }
}
