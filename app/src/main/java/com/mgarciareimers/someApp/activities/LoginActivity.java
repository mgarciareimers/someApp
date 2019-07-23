package com.mgarciareimers.someApp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.commons.Utilities;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, googleLoginButton, facebookLoginButton;
    private EditText emailEditText, passwordEditText;
    private TextView signUpQuestionTextView, signUpTextView;
    private ConstraintLayout progressBarContainer = null;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.defineFields();
        this.defineFieldActions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.firebaseAuth.addAuthStateListener(this.authStateListener);
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
        this.progressBarContainer = findViewById(R.id.progressBarDialog);

        // FireBase.
        this.firebaseAuth = FirebaseAuth.getInstance();

        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null && firebaseUser.isEmailVerified()) {
                    login();
                }
            }
        };
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
            this.firebaseAuth.signInWithEmailAndPassword(this.emailEditText.getText().toString(), this.passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Utilities.presentToast(LoginActivity.this, LoginActivity.this.getString(R.string.credentialsNotCorrect));
                        return;
                    }

                    login();
                }
            });
        } else {
            Utilities.presentToast(this, this.getString(R.string.credentialsNotCorrect));
        }
    }

    // Method that is called when the user clicks the google login button.
    private void onGoogleLoginButtonClicked() {
        Log.d("GoogleLoginButton", "Google login..."); // TODO
    }

    // Method that is called when the user clicked the facebook login button.
    private void onFacebookLoginButtonClicked() {
        Log.d("FacebookLoginButton", "Facebook login..."); // TODO
    }

    // Method that is called when the user click the sign up texts.
    private void onSignUpClicked() {
        this.startActivity(new Intent(this, SignUpActivity.class));
    }

    // Method that logs in.
    private void login() {
        this.startActivity(new Intent(this, TabsActivity.class));
        this.finish();
    }
}