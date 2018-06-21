package com.example.huntertsai.graphicdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by huntertsai on 2018-02-08.
 */

public class AnimateView extends View{

    private static final float BALL_SIZE = 30;
    private float ballX = 30;
    private float ballY = 30;
    private float velocityX = 10;
    private float velocityY = 12f;

    public AnimateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        bouncingBall(canvas);
    }

    public void doAnimation(){
        while (true){
            //pause
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            postInvalidate(); // redraw
        }
    }

    private void bouncingBall(Canvas canvas){
        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(ballX, ballY, BALL_SIZE, bluePaint);
        float width = canvas.getWidth();
        float height = canvas.getHeight();
//        ballX += velocityX;


            ballX += velocityX;
            ballY += velocityY;



        if (ballX + BALL_SIZE >= width || ballX <= 0 + BALL_SIZE){
            velocityX = - velocityX;
        }
        if (ballY + BALL_SIZE <= 0 || ballY >= height - BALL_SIZE){
            velocityY = - velocityY;
        }
    }

    private void drawRandomStuff(Canvas canvas){

        //face
        Paint yellowPaint = new Paint();
        //yellowPaint.setARGB(255, 0 , 0 , 255);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(185, 180, 100, yellowPaint);

        Paint leftEye = new Paint();
        leftEye.setColor(Color.BLUE);
        canvas.drawCircle(140, 140, 15, leftEye);

        Paint rightEye = new Paint();
        rightEye.setColor(Color.BLUE);
        canvas.drawCircle(230, 140, 15, rightEye);

        Paint nose = new Paint();
        nose.setColor(Color.BLACK);
        canvas.drawCircle(190, 180, 10, nose);

        Paint mouth = new Paint();
        mouth.setColor(Color.RED);
        mouth.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(140, 220, 235, 240, mouth);

        Paint textPint = new Paint();
        textPint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC));
        textPint.setTextSize(40);
        canvas.drawText("work?", 100, 400, textPint);


        //Target #1
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.FILL);

        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);
        int size = 350;
        int size2 = 380;

        for (int i = 0; i < 5; i++){

            canvas.drawCircle(380, 400, size2, whitePaint);
            size2 -= 70;
            canvas.drawCircle(380, 400, size, redPaint);
            size -= 70;
        }


        // target #2
//        Paint red = new Paint();
//        red.setARGB(255, 255, 0, 0);
//        Paint white = new Paint();
//        white.setARGB(255, 255, 255, 255);
//
//        int num = 9;
//        int w = canvas.getWidth();
//        for (int i = 0; i < num; i++) {
//            canvas.drawCircle(w / 2, w / 2, w / 2 - (i * 50), i % 2 == 1 ? red: white);
//
//        }

    }


}
