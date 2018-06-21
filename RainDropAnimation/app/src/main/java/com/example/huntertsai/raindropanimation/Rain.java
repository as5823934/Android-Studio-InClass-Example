package com.example.huntertsai.raindropanimation;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by huntertsai on 2018-02-09.
 */

public class Rain {
    private float x;
    private float y;
    private float size;
    private Paint paint;
    private int a, r , g , b;
    private Random random;

    public Rain(float x, float y, float size, Paint paint) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.paint = paint;
    }

    public Rain(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;

        random = new Random();
        a = 255;
        r = random.nextInt(256);
        g = random.nextInt(256);
        b = random.nextInt(256);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public Paint getPaint() {
        Paint paint = new Paint();
        paint.setARGB(a, r, g, b);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        return paint;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
