package com.example.huntertsai.tfquiz;

import java.util.ArrayList;

/**
 * Created by huntertsai on 2018-01-11.
 */

public class QuizLab {
    private int mTextResId;
    private boolean mAnswerTrue;

    public QuizLab(int TextResId, boolean AnswerTrue){
        mTextResId = TextResId;
        mAnswerTrue = AnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int TextResId) {
        this.mTextResId = TextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean AnswerTrue) {
        mAnswerTrue = AnswerTrue;
    }
}
