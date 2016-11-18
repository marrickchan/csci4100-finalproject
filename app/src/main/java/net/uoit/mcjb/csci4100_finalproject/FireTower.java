package net.uoit.mcjb.csci4100_finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2016-11-18.
 */

public class FireTower {
    final private int atkSpeed = 1200;
    final private Timer atkTimer = new Timer();
    private boolean canAtk = true;
    final private int range = 130;
    private ImageView towerSlot;
    private RelativeLayout screen;
    private float x;
    private float y;


    public FireTower(float x, float y, ImageView towerSlot, RelativeLayout screen){
        this.x = x;
        this.y = y;
        System.out.println(x);
        System.out.println(y);
        towerSlot.setImageResource(R.drawable.pikachu);
        this.towerSlot = towerSlot;
        this.screen = screen;
        // Set the timer to check for attacks every second
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            //Called each time when 1000 milliseconds (1 second) (the period parameter)
                if(canAtk = true) {
                    checkCollision();
                }
            }
        },
            //Set how long before to start calling the TimerTask (in milliseconds)
            0,
            //Set the amount of time between each execution (in milliseconds)
            100);


    }

    private void attack(){
        // TESTING
        canAtk = false;
        // TODO:
        // FIX THIS TIMER TO START COUNTING WHEN ATTACKED. THEN IT SETS TRUE AFTER DONE
        atkTimer.schedule(new TimerTask() {
            @Override
            public void run(){
                canAtk = true;
            }
        }, 0, atkSpeed);

    }

    private boolean checkCollision(){
        // Check collision
        // If collided, attack
        // Find center of the tower
        float centerX = (x + towerSlot.getWidth())/2;
        float centerY = (y + towerSlot.getHeight())/2;

        // Check all the items on the screen and detect closeness
        for (int i = 0; i < screen.getChildCount(); i++) {
            View child = screen.getChildAt(i);
            // Only compare objects that are now the same id as tower
            // TODO:
            // If time, detect all towers
            if (child.getId() != towerSlot.getId()){
                // Check Collision (CURRENTLY ONLY CHECKS BY SQUARE)
                // (ALSO ONLY CHECKS IF CENTER OF CHILD IS IN RANGE)

                // Child Center
                float childX = (child.getX() + child.getWidth())/2;
                float childY = (child.getY() + child.getHeight())/2;

                // Check Square Collision
                if ( // Check Horizontally
                        (childX > centerX - range &&
                        childX < centerX + range)
                        &&
                        // Check Vertically
                        (childY > centerY - range &&
                        childY < centerY + range)){
                    attack();
                }
            }
        }
        return false;
    }



}
