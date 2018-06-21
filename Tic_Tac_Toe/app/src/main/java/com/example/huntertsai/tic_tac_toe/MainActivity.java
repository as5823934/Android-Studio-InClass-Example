package com.example.huntertsai.tic_tac_toe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private GridLayout gridLayout;
    private int turn;
    private static ArrayList<String> O;
    private static ArrayList<String> X;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.invisible_text);
        gridLayout = findViewById(R.id.grid_box);


        setupGame();
        O = new ArrayList<>();
        X = new ArrayList<>();

    }

    private void setupGame() {
        textView.setText("player 1");
        textView.setTextSize(18);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);

            imageView.setOnClickListener(this);
        }
        whoFirst();
    }

    @Override
    public void onClick(View view) {
        ImageView imgView = (ImageView) view;

//            switch (turn) {
//                case 1:
//                    player(view, imgView);
//                    break;
//                case 2:
//                    AI(view, imgView);
//                    break;
//            }
        if (turn == 1){
            player(view, imgView);
        }else{
            AI(view, imgView);
        }

            if (O.size() + X.size() == 9 && checkWinner()){
                dialog("Tie");
            }

    }

    private void AI(View view, ImageView imgView) {
        textView.setText("player 1 turn");
        textView.setTextColor(Color.RED);
        imgView.setImageResource(R.drawable.x);
        imgView.setEnabled(false);
        String temp_X = view.getTag().toString();
        X.add(temp_X);
        turn--;
        System.out.println(temp_X);
        System.out.println("player 1: " + X.toString());
        checkWinner();
    }

    private void player(View view, ImageView imgView) {
        textView.setText("player 2 turn");
        textView.setTextColor(Color.BLUE);

        imgView.setImageResource(R.drawable.o);
        imgView.setEnabled(false);
        String temp_O = view.getTag().toString();
        System.out.println(temp_O);
        O.add(temp_O);
        turn++;
        System.out.println("player 1: " + O.toString());
        checkWinner();
    }

    private void whoFirst() {
        Random rand = new Random();
        turn = rand.nextInt(2) + 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("player " + turn + " first");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public boolean checkWinner(){
        boolean gameIsActive = true;
        String[] win0 = {"0","1","2"};
        String[] win1 = {"3","4","5"};
        String[] win2 = {"6","7","8"};
        String[] win3 = {"0","3","6"};
        String[] win4 = {"1","4","7"};
        String[] win5 = {"2","5","8"};
        String[] win6 = {"0","4","8"};
        String[] win7 = {"2","4","6"};
        String[][] wins = {win0, win1, win2, win3, win4, win5, win6, win7};

        for (int i = 0; i < wins.length; i++){
            String[] win = wins[i];
            int o_count = 0;
            int x_count = 0;
            for (int j = 0; j < win.length; j++) {
                String pos = win[j];
                if (O.contains(pos)) {
                    o_count++;
                }
                if (X.contains(pos)) {
                    x_count++;
                }
            }
            if (o_count == 3){
                dialog("player 1 win");
                gameIsActive = false;
            }else if (x_count == 3) {
                dialog("player 2 win");
                gameIsActive = false;
            }
        }
        return gameIsActive;
    }

    public void dialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(msg);
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent t= new Intent(MainActivity.this,MainActivity.class);
                startActivity(t);
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void new_game(View view) {
        Intent t= new Intent(MainActivity.this,MainActivity.class);
        startActivity(t);
        finish();
    }

}
