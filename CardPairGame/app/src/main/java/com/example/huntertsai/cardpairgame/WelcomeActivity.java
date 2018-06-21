package com.example.huntertsai.cardpairgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void toEasyPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toHardPage(View view) {
        Intent intent = new Intent(this, HardActivity.class);
        startActivity(intent);
    }

    public void toHellPage(View view) {
        Intent intent = new Intent(this, HellActivity.class);
        startActivity(intent);
    }
}
