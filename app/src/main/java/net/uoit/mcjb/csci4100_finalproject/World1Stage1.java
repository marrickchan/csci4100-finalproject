package net.uoit.mcjb.csci4100_finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class World1Stage1 extends AppCompatActivity {
    final private int CANCEL_TOWER = 9001;
    final private int FIRE_TOWER = 5006;
    final private int CANNON_TOWER = 5007;
    final private int STAGE_CODE = 0101;
    private GameHelper w1s1Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world1_stage1);



        // Layout
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.activity_world1_stage1);

        // Grab Tower Locations
        ImageView[] towers = new ImageView[8];
        towers[0] = (ImageView)findViewById(R.id.w1s1Tower1);
        towers[1] = (ImageView)findViewById(R.id.w1s1Tower2);
        towers[2] = (ImageView)findViewById(R.id.w1s1Tower3);
        towers[3] = (ImageView)findViewById(R.id.w1s1Tower4);
        towers[4] = (ImageView)findViewById(R.id.w1s1Tower5);
        towers[5] = (ImageView)findViewById(R.id.w1s1Tower6);
        towers[6] = (ImageView)findViewById(R.id.w1s1Tower7);
        towers[7] = (ImageView)findViewById(R.id.w1s1Tower8);

        w1s1Helper = new GameHelper(this, rl, towers, STAGE_CODE);
    }

    public void onStop(){
        System.out.println("Closed Stage 1");
        w1s1Helper.kill();
        finish();

        super.onStop();
    }
}
