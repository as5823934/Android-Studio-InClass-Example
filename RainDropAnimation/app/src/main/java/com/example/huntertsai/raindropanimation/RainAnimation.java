package com.example.huntertsai.raindropanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by huntertsai on 2018-02-08.
 */

public class RainAnimation extends View {

    private Rain rain;
    private ArrayList<Rain> rain_list = new ArrayList<>();
    private int animationTick = 0;


    public RainAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
       rainDrop(canvas);
    }

//    public void runAnimation(){
//        while (true){
//            try {
//                Thread.sleep(1000 / 50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            postInvalidate();//redraw
//        }
//    }

    private void rainDrop(Canvas canvas){

        for (Rain drop : rain_list){
            drop.setY(drop.getY() + 5);
            //drop.setX(drop.getX() + 1);
            canvas.drawCircle(drop.getX(), drop.getY(), drop.getSize(), drop.getPaint());
        }

        animationTick ++;
        if (animationTick % 15 == 0){ // speed of redraw
            float rand = (float) Math.random() * canvas.getWidth();
            Rain drop = new Rain(rand, 0 , 20);
            rain_list.add(drop);
        }


    }
}
