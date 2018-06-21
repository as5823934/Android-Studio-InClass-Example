package com.example.huntertsai.thebiggerthebetter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int num1, num2, points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generate();
    }

    private void generate() {
        //create random numbers for button text
        Random random = new Random();
        num1 = random.nextInt(50);
        while(true){
            num2 = random.nextInt(50);
            if(num1 != num2){
                break;
            }
        }

        //set button text to random number
        Button left = findViewById(R.id.left_button);
        left.setText(Integer.toString(num1));

        Button right = findViewById(R.id.right_button);
        right.setText(Integer.toString(num2));
    }

    private void Compare(int num1, int num2){
        if(num1 > num2){
            points++;
            Toast.makeText(this, "You Are Right!", Toast.LENGTH_SHORT).show();
        } else {
            points--;
            Toast.makeText(this, "You Are Wrong!", Toast.LENGTH_SHORT).show();
        }
        // update UI and refresh number
        TextView pointView = findViewById(R.id.points);
        pointView.setText("Points: " + points);
        generate();
    }

    public void leftButtonClicked(View view) {
        Compare(num1, num2);
    }

    public void rightButtonClicked(View view) {
        Compare(num2, num1);

    }

}
