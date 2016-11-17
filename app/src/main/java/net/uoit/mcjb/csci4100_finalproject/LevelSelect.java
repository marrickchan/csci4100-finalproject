package net.uoit.mcjb.csci4100_finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LevelSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        // Get Buttons
        Button w1s1SelectButton = (Button)findViewById(R.id.w1s1Select);
        Button w1s2SelectButton = (Button)findViewById(R.id.w1s2Select);


        // Start Game
        w1s1SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w1s1 = new Intent(LevelSelect.this, World1Stage1.class);
                startActivity(w1s1);
            }
        });
    }
}
