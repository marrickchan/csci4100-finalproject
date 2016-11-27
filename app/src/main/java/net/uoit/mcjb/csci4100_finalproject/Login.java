package net.uoit.mcjb.csci4100_finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static net.uoit.mcjb.csci4100_finalproject.MainActivity.EXTRA_USERNAME;

public class Login extends AppCompatActivity {
    EditText username;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.usernameField_Login);
        loginButton = (Button)findViewById(R.id.loginButton_Login);

        // Login with username
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_USERNAME, username.getText().toString());
                setResult(Login.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
