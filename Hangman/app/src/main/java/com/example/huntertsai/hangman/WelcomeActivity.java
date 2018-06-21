package com.example.huntertsai.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class WelcomeActivity extends AppCompatActivity {

    private HTextView hTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        hTextView = (HTextView) findViewById(R.id.htext);
        hTextView.setAnimateType(HTextViewType.TYPER);
        hTextView.animateText("What do you want to play today?"); // animate
    }

    public void to_Tic_tac_toe_player(View view) {
        Intent intent = new Intent(this, TicTacToe_Player_Activity.class);
        startActivity(intent);
    }

    public void to_Tic_tac_toe_ai(View view) {
        Intent intent = new Intent(this, TicTacToe_AI_Activity.class);
        startActivity(intent);
    }

    public void to_Hangman(View view) {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hTextView = (HTextView) findViewById(R.id.htext);
        hTextView.setAnimateType(HTextViewType.TYPER);
        hTextView.animateText("What do you want to play today?"); // animate
    }
}
