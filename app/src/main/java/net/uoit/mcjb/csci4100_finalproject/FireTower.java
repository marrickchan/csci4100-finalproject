package net.uoit.mcjb.csci4100_finalproject;

import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2016-11-18.
 */

public class FireTower {
    final private int atkSpeed = 1200;
    final private Timer atkTimer = new Timer();
    private boolean canAtk = true;
    final private int range = 50;
    private float x;
    private float y;


    public FireTower(float x, float y, ImageView towerSlot){
        this.x = x;
        this.y = y;
        System.out.println(x);
        System.out.println(y);
        towerSlot.setImageResource(R.drawable.pikachu);

        // Set the timer to check for attacks every second
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            //Called each time when 1000 milliseconds (1 second) (the period parameter)
                if(canAtk = true) {
                    attack();
                }
            }
        },
            //Set how long before to start calling the TimerTask (in milliseconds)
            0,
            //Set the amount of time between each execution (in milliseconds)
            100);


    }

    private void attack(){
        // If there is an enemy to attack, attack
        if(checkCollision()){
            canAtk = false;
        }

        // TESTING
        canAtk = false;
        System.out.println("Attacking");
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



        return false;
    }



}
