package net.uoit.mcjb.csci4100_finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.PluginStub;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Buttons
        Button startGameButton = (Button)findViewById(R.id.startGame_MainScreen);
        Button instructionsButton = (Button)findViewById(R.id.instruction_MainScreen);
        Button highscoresButton = (Button)findViewById(R.id.highscores_MainScreen);
        Button exitButton = (Button)findViewById(R.id.exit_MainScreen);

        // Set the logo for the main menu
        // TODO: Set the logo on the main menu
        ImageView logoMainScreen = (ImageView)findViewById(R.id.logo_MainScreen);

        // Start Game
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGameIntent = new Intent(MainActivity.this, LevelSelect.class);
                startActivity(startGameIntent);
            }
        });

        // Instructions Screen
        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructionsIntent = new Intent(MainActivity.this, Instructions.class);
                startActivity(instructionsIntent);
            }
        });

        // High Scores Screen (Doing web and/or google play services?)
        highscoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highscoresIntent = new Intent(MainActivity.this, HighScores.class);
                startActivity(highscoresIntent);
            }
        });

        // Exit the game
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
