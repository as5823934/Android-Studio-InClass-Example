package com.example.huntertsai.colorquiz2;

/**
 * Created by huntertsai on 2018-01-10.
 */

public class QuizModel {
    private String resultL, resultR, textRnd;
    private int colorL, colorR;


    public QuizModel(int colorL, String left, int colorR, String right, String text) {
        this.resultL = left;
        this.colorL = colorL;
        this.resultR = right;
        this.colorR = colorR;
        this.textRnd = text;
    }

    public  boolean isAnswer(boolean left){
        if(left){
            return resultL.equals(textRnd);
        } else{
            return resultR.equals(textRnd);
        }
    }

    public int getColorL() {
        return colorL;
    }

    public int getColorR() {
        return colorR;
    }

    public String getResultL() {
        return resultL;
    }

    public String getResultR() {
        return resultR;
    }

    public String getTextRnd() {
        return textRnd;
    }
}
