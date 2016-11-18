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
    final private float moveSpeed = 2.0f;
    private int goblinHP = 5;
    private ImageView iv;
    private int currentStage;

    public Goblin(Context context, RelativeLayout screen, int stage){
        currentStage = stage;
        // Set the image
        iv = new ImageView(context);
        iv.setImageResource(R.drawable.pikachu);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30, 10, 0, 0);
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
        if(stage == 0101) {
            if(iv.getX() < 800 && iv.getY() < 250){
                iv.setY(iv.getY() + moveSpeed);
            }
        }
    }


}
