package com.mgarciareimers.someApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.commons.Utilities;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, googleLoginButton, facebookLoginButton;
    private EditText emailEditText, passwordEditText;
    private TextView signUpQuestionTextView, signUpTextView;

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
        this.googleLoginButton = this.findViewById(R.id.googleLoginButton);
        this.facebookLoginButton = this.findViewById(R.id.facebookLoginButton);
        this.emailEditText = this.findViewById(R.id.emailEditText);
        this.passwordEditText = this.findViewById(R.id.passwordEditText);
        this.signUpQuestionTextView = this.findViewById(R.id.signUpQuestionTextView);
        this.signUpTextView = this.findViewById(R.id.signUpTextView);
    }

    // Method that defines the actions of the fields.
    private void defineFieldActions() {
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
            }
        });
        this.googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onGoogleLoginButtonClicked();
            }
        });
        this.facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onFacebookLoginButtonClicked();
            }
        });
        this.signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClicked();
            }
        });
        this.signUpQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onSignUpClicked();
            }
        });
    }

    // Method that is called when the user clicks the login button.
    private void onLoginButtonClicked() {
        if (Utilities.emailIsValid(this.emailEditText.getText().toString()) && Utilities.passwordIsValid(this.passwordEditText.getText().toString())) {
            Log.d("LoginButton", "Login...");
        } else {
            Utilities.presentToast(this, this.getString(R.string.credentialsNotCorrect));
        }
    }

    // Method that is called when the user clicks the google login button.
    private void onGoogleLoginButtonClicked() {
        Log.d("GoogleLoginButton", "Google login...");
    }

    // Method that is called when the user clicked the facebook login button.
    private void onFacebookLoginButtonClicked() {
        Log.d("FacebookLoginButton", "Facebook login...");
    }

    // Method that is called when the user click the sign up texts.
    private void onSignUpClicked() {
        this.startActivity(new Intent(this, SignUpActivity.class));
    }
}