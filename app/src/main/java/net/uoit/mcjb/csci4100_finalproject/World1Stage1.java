package net.uoit.mcjb.csci4100_finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class World1Stage1 extends AppCompatActivity {

    private int lives = 10;
    private int gold = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world1_stage1);


        // Initialize Information Bar
        TextView infoBar = (TextView)findViewById(R.id.infoBar);
        infoBar.setText("Lives: " + lives + "  | Gold: " + gold);

        // Grab Tower Locations
        ImageView tower1 = (ImageView)findViewById(R.id.w1s1Tower1);
        ImageView tower2 = (ImageView)findViewById(R.id.w1s1Tower2);
        ImageView tower3 = (ImageView)findViewById(R.id.w1s1Tower3);
        ImageView tower4 = (ImageView)findViewById(R.id.w1s1Tower4);
        ImageView tower5 = (ImageView)findViewById(R.id.w1s1Tower5);
        ImageView tower6 = (ImageView)findViewById(R.id.w1s1Tower6);
        ImageView tower7 = (ImageView)findViewById(R.id.w1s1Tower7);
        ImageView tower8 = (ImageView)findViewById(R.id.w1s1Tower8);

        tower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(World1Stage1.this, "First Tower Clicked", Toast.LENGTH_SHORT).show();
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
}
