package com.example.huntertsai.colorquiz;

import android.graphics.Color;

/**
 * Created by huntertsai on 2018-01-10.
 */

public class QuizModel{
    private String[] color = new String[]{"Blue", "Red", "Yellow", "Green", "Magenta", "Gray", "Black", "LTgray", "DKgray", "Cyan"};
    private int[] colorInt = new int[]{Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.GRAY, Color.BLACK,
            Color.LTGRAY, Color.DKGRAY, Color.CYAN};


    public QuizModel(){

    }

    public String[] getColor() {
        return color;
    }

    public int[] getColorInt() {
        return colorInt;
    }


}
