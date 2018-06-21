package com.example.huntertsai.hangman;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class TicTacToe_Player_Activity extends AppCompatActivity {

    private GameBoard board = null;
    private int moveCount = 0, xloc = 0, yloc = 0;
    private String mark = "X", aiMark = "O";
    private boolean isOver = false;
    private AI ai = null;
    private int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_player);
        //Set up a new board and AI and assign the initial variables
        board = new GameBoard();
        ai = new AI(aiMark);

    }

    //Action when reset is clicked which clears the screen and the virtual game board
    public void resetClick(View v) {
        clear();
//        if (aiMark == "X") getAIMove(board);

    }

    //Action for when a cell is clicked. Determines which cell has been clicked and passed that
    // information on the the virtual game board.
    public void cellClick(View v) {
        //Get the id of the clicked object and assign it to a Textview variable
        HTextView cell = findViewById(v.getId());

        //Check the content and make sure the cell is empty and that the game isn't over
        String content = (String) cell.getText();
        playWithPlayer(cell, content);

    }

    private void playWithPlayer(HTextView cell, String content) {
        if (turn == 1){
            System.out.println(turn);
            if (content == "" && !isOver) {
                //Find the X Y location values of the particular cell that was clicked
                switch (cell.getId()) {
                    case R.id.cell_11:
                        xloc = 0;
                        yloc = 0;
                        break;
                    case R.id.cell_12:
                        xloc = 0;
                        yloc = 1;
                        break;
                    case R.id.cell_13:
                        xloc = 0;
                        yloc = 2;
                        break;
                    case R.id.cell_21:
                        xloc = 1;
                        yloc = 0;
                        break;
                    case R.id.cell_22:
                        xloc = 1;
                        yloc = 1;
                        break;
                    case R.id.cell_23:
                        xloc = 1;
                        yloc = 2;
                        break;
                    case R.id.cell_31:
                        xloc = 2;
                        yloc = 0;
                        break;
                    case R.id.cell_32:
                        xloc = 2;
                        yloc = 1;
                        break;
                    case R.id.cell_33:
                        xloc = 2;
                        yloc = 2;
                        break;
                }
                turn++;
                //Place the player's mark on the specific X Y location on both the virtual and displayed board
                board.placeMark(xloc, yloc, mark);
                cell.animateText(mark);
                //Increment move Count because a move was just made
                moveCount++;
                //Check to see if the game is over
                isOver = checkEnd(mark);
                //if the game is over get the AI's move
//                if (!isOver)
//                    getAIMove(board);
            }
        }else{
            System.out.println(turn);
            if (content == "" && !isOver) {
                //Find the X Y location values of the particular cell that was clicked
                switch (cell.getId()) {
                    case R.id.cell_11:
                        xloc = 0;
                        yloc = 0;
                        break;
                    case R.id.cell_12:
                        xloc = 0;
                        yloc = 1;
                        break;
                    case R.id.cell_13:
                        xloc = 0;
                        yloc = 2;
                        break;
                    case R.id.cell_21:
                        xloc = 1;
                        yloc = 0;
                        break;
                    case R.id.cell_22:
                        xloc = 1;
                        yloc = 1;
                        break;
                    case R.id.cell_23:
                        xloc = 1;
                        yloc = 2;
                        break;
                    case R.id.cell_31:
                        xloc = 2;
                        yloc = 0;
                        break;
                    case R.id.cell_32:
                        xloc = 2;
                        yloc = 1;
                        break;
                    case R.id.cell_33:
                        xloc = 2;
                        yloc = 2;
                        break;
                }
                turn--;
                //Place the player's mark on the specific X Y location on both the virtual and displayed board
                board.placeMark(xloc, yloc, aiMark);
                cell.setAnimateType(HTextViewType.SCALE);
                cell.animateText(aiMark);
                //Increment move Count because a move was just made
                moveCount++;
                //Check to see if the game is over
                isOver = checkEnd(aiMark);
            }

        }
    }

    //Checks to see if the game has ended provided with the last player to make a move
    private boolean checkEnd(String player) {
        //Checks the virtual board for a winner if there's a winner announce it with the provided player
        if (board.isWinner())
        {
            announce(true, player);
            return true;
        }

        //Check to see if we've reached our move total meaning it's a draw
        else if (moveCount >= 9)
        {
            announce(false, player);
            return true;
        }
        //If neither win or draw then the game is still on
        return false;

    }

    //Announce the winner, given a boolean for whether it was a win or a draw
    // and given the last player to make a mark
    private void announce(boolean endState, String player) {
        //Check for if it's a win or a draw. if it's a win amend player with wins!
        // if it's a lose replace player with it's a draw! I did this just because why
        // declare another String when I can just reuse the one I have?
        if (endState == true)
        {
            player = player + " wins!";
        }
        else
        {
            player = "It's a draw!";
        }

        //Get the application Context and setup the Toast notification with the end state info
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, player, Toast.LENGTH_LONG);
        toast.show();
    }

    //Clears the game Board
    private void clear() {
        //Get the id list of all the Textview cells
        int[] idList = { R.id.cell_11, R.id.cell_12, R.id.cell_13, R.id.cell_21, R.id.cell_22, R.id.cell_23, R.id.cell_31, R.id.cell_32, R.id.cell_33 };
        HTextView cell;
        //For each cell clear the text with an empty string
        for (int item : idList) {
            cell = findViewById(item);
            cell.animateText("");

        }

        //Reset the game state and clear the virtual board
        isOver = false;
        moveCount = 0;
        board.clear();
    }
}
