package net.uoit.mcjb.csci4100_finalproject;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2016-11-18.
 */

public class Goblin {
    final private float moveSpeed = 10.0f;
    private int goblinHP = 5;
    private ImageView iv;
    private RelativeLayout screen;
    private int currentStage;

    public Goblin(Context context, RelativeLayout screen, int stage){
        currentStage = stage;
        // Set the image
        this.screen = screen;
        iv = new ImageView(context);
        iv.setImageResource(R.drawable.pikachu);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30, 0, 0, 0);
        screen.addView(iv, lp);


        // Set the timer to walk through the stage
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                walk(currentStage);
            }
        },
            //Set how long before to start calling the TimerTask (in milliseconds)
            0,
            //Set the amount of time between each execution (in milliseconds)
            100);

    }

    private void walk(int stage){
        float xcoord = iv.getX();
        float ycoord = iv.getY();
        if(stage == 0101) {
            // If reaches the end, REMOVE A LIFE
            // TODO:
            // REMOVE A LIFE
            if(ycoord > 1280){
                // TODO:
                // DELETE GOBLIN???
            // Last turn into exit (+y)
            } else if(xcoord > 920 && ycoord > 930) {
                iv.setY(iv.getY() + moveSpeed);
            // Last across (+x)
            } else if(xcoord < 930 && ycoord > 930){
                iv.setX(iv.getX() + moveSpeed);
            // Second last down (+y)
            } else if(xcoord < 40 && (ycoord > 610 && ycoord < 940)){
                iv.setY(iv.getY() + moveSpeed);
            // Across Left (-x)
            } else if(xcoord > 30 && (ycoord > 610 && ycoord < 940)) {
                iv.setX(iv.getX() - moveSpeed);
            // Second down (+y)
            } if(xcoord > 920 && (ycoord > 290 && ycoord < 620)) {
                iv.setY(iv.getY() + moveSpeed);
            // first right (+x)
            } else if(xcoord < 930 && (ycoord > 290 && ycoord < 620)) {
                iv.setX(iv.getX() + moveSpeed);
            // First down (+y)
            } else if(xcoord < 40 && ycoord < 300){
                iv.setY(iv.getY() + moveSpeed);
            }
        }
    }


}
