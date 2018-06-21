package com.example.huntertsai.colorquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int result, points = 0;
    private int colorLid;
    private int colorRid;
    private String temp;
    private String resultL;
    private String resultR;
    private QuizModel colorL = new QuizModel();
    private QuizModel colorR = new QuizModel();
    private QuizModel colorIdL = new QuizModel();
    private QuizModel colorIdR  = new QuizModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generator();

    }

    private void generator() {
        Button left = findViewById(R.id.leftButton);
        Button right = findViewById(R.id.rightButton);
        TextView answer = findViewById(R.id.answer);

        Random random = new Random();
        int let = random.nextInt(10);
        colorLid = colorIdL.getColorInt()[let];
        resultL = colorL.getColor()[let];
        left.setBackgroundColor(colorLid);

        while (true){
            int rit = random.nextInt(10);
            colorRid = colorIdR.getColorInt()[rit];
            resultR = colorR.getColor()[rit];
            right.setBackgroundColor(colorRid);
            if(let != rit){
                break;
            }
        }

        result = random.nextInt(2);
        if(result == 0){
            answer.setText(resultL);
            temp = resultL;

        } else{
            answer.setText(resultR);
            temp = resultR;

        }
    }

    public void leftClicked(View view) {
        if(resultL.equals(temp)){
            points++;
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        } else{
            points--;
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
        TextView scoreView = findViewById(R.id.points);
        scoreView.setText("Score: " + points);
        generator();
    }

    public void rightClicked(View view) {
        if(resultR.equals(temp)){
            points++;
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        } else{
            points--;
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
        TextView scoreView = findViewById(R.id.points);
        scoreView.setText("Score: " + points);
        generator();
    }

}
