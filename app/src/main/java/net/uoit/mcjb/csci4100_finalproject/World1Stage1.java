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

public class World1Stage1 extends AppCompatActivity {
    final private int CANCEL_TOWER = 9001;
    final private int FIRE_TOWER = 5006;
    final private int CANNON_TOWER = 5007;
    final private int STAGE_CODE = 0101;
    private int lives = 10;
    private int gold = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world1_stage1);


        // Initialize Information Bar
        TextView infoBar = (TextView)findViewById(R.id.infoBar);
        infoBar.setText("Lives: " + lives + "  | Gold: " + gold);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.activity_world1_stage1);
        // Grab Tower Locations
        ImageView tower1 = (ImageView)findViewById(R.id.w1s1Tower1);
        ImageView tower2 = (ImageView)findViewById(R.id.w1s1Tower2);
        ImageView tower3 = (ImageView)findViewById(R.id.w1s1Tower3);
        ImageView tower4 = (ImageView)findViewById(R.id.w1s1Tower4);
        ImageView tower5 = (ImageView)findViewById(R.id.w1s1Tower5);
        ImageView tower6 = (ImageView)findViewById(R.id.w1s1Tower6);
        ImageView tower7 = (ImageView)findViewById(R.id.w1s1Tower7);
        ImageView tower8 = (ImageView)findViewById(R.id.w1s1Tower8);

        Goblin gob1 = new Goblin(this, rl, 0101);

        rl.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg){
                System.out.println("Clicked: " + arg.getX() + ", " +  arg.getY());
                return true;
            }
        });


        tower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "First Tower Clicked", Toast.LENGTH_SHORT).show();
                // Creating Tower
                // TODO:
                // Create a window that will let user choose tower to build
                int chosenTower = chooseTower();
                if(chosenTower == FIRE_TOWER){
                    ImageView tower1 = (ImageView)findViewById(R.id.w1s1Tower1);
                    FireTower t1 = new FireTower(tower1.getX(), tower1.getY(), tower1);
                } else {

                }

            }
        });
        tower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "Second Tower Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tower3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "Third Tower Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tower4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "Fourth Tower Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tower5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "Fifth Tower Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tower6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "Sixth Tower Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tower7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "Seventh Tower Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tower8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "Eighth Tower Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int chooseTower(){
        return FIRE_TOWER;
    }
}
