package com.example.huntertsai.mydictionary;

/**
 * Created by huntertsai on 2018-01-25.
 */

public class MyBounceInterpolator implements android.view.animation.Interpolator{

    private double mAmplitude = 1;
    private double mFrequency = 10;

    MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
