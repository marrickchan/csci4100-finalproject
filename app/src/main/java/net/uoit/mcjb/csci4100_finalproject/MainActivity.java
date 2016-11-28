package net.uoit.mcjb.csci4100_finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    final static String LOGOUT_CONSTANT = "Logout";
    final static String LOGIN_CONSTANT = "Login";
    final static String LOGGED_OUT_STATUS_CONSTANT = "Please log in.";
    final static int USERNAME_REQUEST = 1;
    final static String EXTRA_USERNAME = "a100504990.logintime.username";
    final static String USERNAME_OUT_FILE = "username.out";


    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private DatabaseReference databaseReference;
    private EditText editTextName, editTextAddress;
    private Button buttonSave;

    TextView status;
    Button logButton;
    File file;
    Boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Buttons
        Button startGameButton = (Button) findViewById(R.id.startGame_MainScreen);
        Button instructionsButton = (Button) findViewById(R.id.instruction_MainScreen);
        Button highscoresButton = (Button) findViewById(R.id.highscores_MainScreen);
        Button exitButton = (Button) findViewById(R.id.exit_MainScreen);

        status = (TextView) findViewById(R.id.status_MainScreen);
        logButton = (Button) findViewById(R.id.logButton_MainScreen);

        // Set the logo for the main menu
        // TODO: Set the logo on the main menu
        ImageView logoMainScreen = (ImageView) findViewById(R.id.logo_MainScreen);

        // Login/Logout button
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loggedIn) {
                    setLogout();
                    writeToFile("");
                } else {
                    Intent startGameIntent = new Intent(MainActivity.this, Login.class);
                    startActivityForResult(startGameIntent, USERNAME_REQUEST);
                }
            }
        });

        // Start Game
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (loggedIn) {
                    Intent startGameIntent = new Intent(MainActivity.this, LevelSelect.class);
                    startActivity(startGameIntent);
                //} else {
                //    Toast.makeText(getApplicationContext(), "Must be logged in to play.", Toast.LENGTH_LONG).show();

                //}
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

    // Get username from Login activity and write to file
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USERNAME_REQUEST) {
            if (resultCode == RESULT_OK) {
                String username = data.getStringExtra(EXTRA_USERNAME);
                setLogin(username);
                writeToFile(username);

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        readFileIntoStatus();
    }

    void writeToFile(String username) {
        FileOutputStream outputStream;
        file = new File(this.getFilesDir(), USERNAME_OUT_FILE);

        try {
            outputStream = openFileOutput(USERNAME_OUT_FILE, MODE_PRIVATE);
            outputStream.write(username.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFileIntoStatus() {
        file = new File(this.getFilesDir(), USERNAME_OUT_FILE);
        String username;
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                username = scanner.nextLine();
                setLogin(username);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Functions to set login status/text
    public void setLogin(String username) {
        loggedIn = true;
        setStatus(LOGOUT_CONSTANT, "Logged in as " + username);
    }
    public void setLogout() {
        loggedIn = false;
        setStatus(LOGIN_CONSTANT, LOGGED_OUT_STATUS_CONSTANT);
    }
    public void setStatus(String logType, String mainStatus) {
        status.setText(mainStatus);
        logButton.setText(logType);
    }

    // Save to firebase database
    private void saveUserInformation(String name, long highScore) {

        Score score = new Score(name, highScore);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            databaseReference.child(user.getUid()).setValue(user);
        }
        Toast.makeText(this, "Information Saved to firebase", Toast.LENGTH_LONG).show();

    }
}
