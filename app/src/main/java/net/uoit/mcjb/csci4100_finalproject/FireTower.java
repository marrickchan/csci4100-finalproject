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
    final private int atkSpeed = 2500;
    final private Timer atkTimer = new Timer();
    final private int range = 130;
    private ImageView towerSlot;
    private RelativeLayout screen;
    private float x;
    private float y;


    public FireTower(float x, float y, ImageView towerSlot, RelativeLayout screen){
        this.x = x;
        this.y = y;
        towerSlot.setImageResource(R.drawable.pikachu);
        this.towerSlot = towerSlot;
        this.screen = screen;
    }

    public int getAtkSpeed(){
        return atkSpeed;
    }

    public ImageView getImageView(){
        return towerSlot;
    }

    public float returnX(){
        return x;
    }

    public float returnY(){
        return y;
    }

    public int returnRange(){
        return range;
    }

    public RelativeLayout returnRL(){
        return screen;
    }



}
