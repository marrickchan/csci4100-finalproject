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
    Timer t = new Timer();
    private int enemyNumber; // Number of enemy position in wave (used for goblin deletion/score)

    public Goblin(Context context, RelativeLayout screen, int stage, int enemyNumber){
        currentStage = stage;
        this.enemyNumber = enemyNumber;
        // Set the image
        this.screen = screen;
        iv = new ImageView(context);
        iv.setImageResource(R.drawable.pikachu);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30, 0, 0, 0);
        this.screen.addView(iv, lp);
    }

    public int getEnemyNumber(){
        return enemyNumber;
    }

    public float getX(){
        return iv.getX();
    }

    public float getY(){
        return iv.getY();
    }

    public float getWidth(){
        return iv.getWidth();
    }

    public float getHeight(){
        return iv.getHeight();
    }

    public boolean kill(){
        iv.setY(5000.0f);
        iv.setX(5000.0f);
        return true;
    }

    public void start(){
        // Set the timer to walk through the stage
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                boolean end = walk(currentStage);
                if(end){
                    t.cancel();
                }
            }
        },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                100);
    }

    private boolean walk(int stage){
        // Moving by DP
        //final float scale = getContext().getResources().getDisplayMetrics().density;
        //int pixels = (int) (dps * scale + 0.5f);

        // Getting current values
        float xcoord = iv.getX();
        float ycoord = iv.getY();
        if(stage == 0101) {
            // If reaches the end, REMOVE A LIFE
            // TODO:
            // REMOVE A LIFE
            if(ycoord > 1280){
                kill();
                return true;
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
        return false;
    }

    public int getHP(){
        return goblinHP;
    }

    public boolean setHP(int hp){
        goblinHP = hp;
        if(hp <= 0){
            kill();
            return true;
        }
        return false;
    }


    public ImageView getImageView(){
        return iv;
    }


}
