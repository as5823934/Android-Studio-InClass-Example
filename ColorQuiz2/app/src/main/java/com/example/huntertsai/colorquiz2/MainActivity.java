package com.example.huntertsai.colorquiz2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.BLACK;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.CYAN;
import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class MainActivity extends AppCompatActivity {

    private Button leftBtn, rightBtn;
    private TextView answer;

    private int points = 0;
    private ArrayList<QuizModel> questions;
    private QuizModel pick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftBtn = findViewById(R.id.leftButton);
        rightBtn = findViewById(R.id.rightButton);
        answer = findViewById(R.id.answer);

        questions = new ArrayList<>();
        questions.add(new QuizModel(BLUE, "Blue", RED, "Red", "Blue"));
        questions.add(new QuizModel(BLACK,"Black", GRAY,"Gray", "Gray"));
        questions.add(new QuizModel(YELLOW, "Yellow", BLUE,"Blue", "Yellow"));
        questions.add(new QuizModel(RED,"Red", GREEN,"Green", "Red"));
        questions.add(new QuizModel(GRAY,"Gray", YELLOW,"Yellow", "Gray"));
        questions.add(new QuizModel(CYAN,"Cyan", DKGRAY,"DkGray", "Cyan"));
        questions.add(new QuizModel(LTGRAY,"LTgray", GREEN,"Green", "LTgray"));
        questions.add(new QuizModel(BLUE,"Blue", DKGRAY,"Dkgray", "DKgray"));
        questions.add(new QuizModel(GREEN,"Green", CYAN,"Cyan", "Green"));
        questions.add(new QuizModel(RED,"Red", BLACK,"Black", "Black"));
        questions.add(new QuizModel(DKGRAY,"Dkgray", GRAY,"Gray", "Gray"));
        generator();

    }

    private void generator() {
        Random random = new Random();
        pick = questions.get(random.nextInt(questions.size()));
        leftBtn.setBackgroundColor(pick.getColorL());
        rightBtn.setBackgroundColor(pick.getColorR());
        answer.setText(pick.getTextRnd());
    }

    private void check(boolean left){
        if(pick.isAnswer(left)){
            points++;
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        } else {
            points--;
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
        TextView score = findViewById(R.id.points);
        score.setText("Score: " + points);
        generator();
    }

    public void leftClicked(View view) {
        check(true);
    }

    public void rightClicked(View view) {

       check(false);
    }
}
