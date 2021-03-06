package com.example.huntertsai.tfquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private TextView scoreView;
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private int count = 1;
    private String avg;

    private boolean mIsCheater;
    private int mCurrentIndex = 0;
    private double score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);


        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            score = savedInstanceState.getDouble("SCORE",0);
        }



        mQuestionTextView = findViewById(R.id.question_text_view);
        scoreView = findViewById(R.id.score);
        updateQuestion();



        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(false);

            }
        });

        mNextButton = findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getScore();

            }
        });

        mPrevButton = findViewById(R.id.pre_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mCurrentIndex > 1){
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }else{
                    mCurrentIndex = (mCurrentIndex + mQuestionBank.length - 1) % mQuestionBank.length;
                    count--;
                }
                updateQuestion();

            }
        });

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
               Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode != Activity.RESULT_OK){
            return;
        }
        if (resultCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private void getScore() {
        if(count > mQuestionBank.length){
            NumberFormat myformat = NumberFormat.getPercentInstance();
            avg = "Your Average Score is : " + myformat.format(score / mQuestionBank.length);
            Toast.makeText(getApplicationContext(), avg, Toast.LENGTH_LONG).show();
        }
        else{
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            mIsCheater = false;
            updateQuestion();
            count++;
        }
    }

    private QuizLab[] mQuestionBank = new QuizLab[]{
            new QuizLab(R.string.question_australia, false),
            new QuizLab(R.string.question_oceans, true),
            new QuizLab(R.string.question_midest, false),
            new QuizLab(R.string.question_africa, false),
            new QuizLab(R.string.question_americas, true),
            new QuizLab(R.string.question_asia, true)
    };


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (mIsCheater){
            messageResId = R.string.judgment_toast;
        }else{
            if(userPressedTrue == answerIsTrue){
                updateQuestion();
                score++;
                messageResId = R.string.correct_Toast;
            } else {
                messageResId = R.string.incorrect_Toast;
                score--;
            }

            scoreView.setText("Score: " + score);
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putDouble("SCORE", score);
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }






}
