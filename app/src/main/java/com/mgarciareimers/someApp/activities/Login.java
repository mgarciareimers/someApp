package com.mgarciareimers.someApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.commons.Utilities;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.defineFields();
        this.defineFieldActions();
    }

    // Method that defines the fields.
    private void defineFields() {
        this.loginButton = this.findViewById(R.id.loginButton);
        this.emailEditText = this.findViewById(R.id.emailEditText);
        this.passwordEditText = this.findViewById(R.id.passwordEditText);
    }

    // Method that defines the actions of the fields.
    private void defineFieldActions() {
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
            }
        });
    }

    // Method that is called when the user clicks the login button.
    private void onLoginButtonClicked() {
        if (Utilities.emailIsValid(this.emailEditText.getText().toString()) && Utilities.passwordIsValid(this.passwordEditText.getText().toString())) {
            Log.d("LoginButton", "Login...!");
        } else {
            Log.d("LoginButton", "Not valid!");
        }
    }
}