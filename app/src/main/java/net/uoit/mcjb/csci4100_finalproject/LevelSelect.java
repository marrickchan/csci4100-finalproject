package net.uoit.mcjb.csci4100_finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.logging.Level;

import static net.uoit.mcjb.csci4100_finalproject.MainActivity.EXTRA_SCORE;
import static net.uoit.mcjb.csci4100_finalproject.MainActivity.SCORE_REQUEST;

public class LevelSelect extends AppCompatActivity {
    final static int LEVEL_SCORE_REQUEST = 3;
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

                Intent startGameIntent = new Intent(LevelSelect.this, World1Stage1.class);
                startActivityForResult(startGameIntent, LEVEL_SCORE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LEVEL_SCORE_REQUEST) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(EXTRA_SCORE, 0);

                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_SCORE, score);
                setResult(Login.RESULT_OK, resultIntent);
            }
        }

    }
}
