package com.example.huntertsai.colormatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String[] colorBox = {"RED", "BLUE", "GREEN"};
    private int color, points = 0;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorGen();
    }

    private void colorGen() {
        Random random = new Random();
        color = random.nextInt(3);
        result = colorBox[color];
        TextView colorView = findViewById(R.id.colorBox);
        if(result.equalsIgnoreCase("RED")){
            colorView.setBackgroundColor(-65536);
        } else if(result.equalsIgnoreCase("BLUE")){
            colorView.setBackgroundColor(-16776961);
        } else{
            colorView.setBackgroundColor(-16711936);
        }
    }

    private void compare(String color){
        if(color.equalsIgnoreCase(result)){
            points++;
            Toast.makeText(this, "You Are Right!", Toast.LENGTH_SHORT).show();
        }else {
            points--;
            Toast.makeText(this, "You Are Wrong!", Toast.LENGTH_SHORT).show();
        }
        TextView pointView = findViewById(R.id.points);
        pointView.setText("Points: " + points);
        colorGen();

    }

    public void redClicked(View view) {
        compare("red");
//        Button red = findViewById(R.id.buttonRed);
//
//        if(result.equalsIgnoreCase(String.valueOf(red.getText()))){
//            points++;
//        }else {
//            points--;
//        }
//        TextView pointView = findViewById(R.id.points);
//        pointView.setText("Points: " + points);
//        colorGen();
    }

    public void blueClicked(View view) {
        compare("blue");
//        Button blue = findViewById(R.id.buttonBlue);
//        if(result.equalsIgnoreCase(String.valueOf(blue.getText()))){
//            points++;
//        }else {
//            points--;
//        }
//        TextView pointView = findViewById(R.id.points);
//        pointView.setText("Points: " + points);
//        colorGen();
    }

    public void greenClicked(View view) {
        compare("green");
//        Button green = findViewById(R.id.buttonGreen);
//        if(result.equalsIgnoreCase(String.valueOf(green.getText()))){
//            points++;
//        }else {
//            points--;
//        }
//        TextView pointView = findViewById(R.id.points);
//        pointView.setText("Points: " + points);
//        colorGen();
    }
}
