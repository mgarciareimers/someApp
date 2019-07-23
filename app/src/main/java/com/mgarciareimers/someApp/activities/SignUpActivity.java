package com.mgarciareimers.someApp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.commons.Constants;
import com.mgarciareimers.someApp.commons.Utilities;

import java.security.Signature;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private CheckBox acceptTermsCheckBox;
    private Button signUpButton, googleSignUpButton, facebookSignUpButton;
    private ConstraintLayout progressBarContainer = null;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.defineFields();
        this.defineFieldActions();
    }

    // Method that defines the fields.
    private void defineFields() {
        this.emailEditText = this.findViewById(R.id.emailEditText);
        this.passwordEditText = this.findViewById(R.id.passwordEditText);
        this.acceptTermsCheckBox = this.findViewById(R.id.acceptTermsCheckBox);
        this.signUpButton = this.findViewById(R.id.signUpButton);
        this.googleSignUpButton = this.findViewById(R.id.googleSignUpButton);
        this.facebookSignUpButton = this.findViewById(R.id.facebookSignUpButton);
        this.progressBarContainer = findViewById(R.id.progressBarDialog);

        // FireBase.
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    // Method that defines the actions of the fields.
    private void defineFieldActions() {
        this.acceptTermsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAcceptTermsCheckboxClicked();
            }
        });

        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpButtonClicked();
            }
        });

        this.googleSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGoogleSignUpButtonClicked();
            }
        });

        this.facebookSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFacebookSignUpButtonClicked();
            }
        });

    }

    // Method that is called when the user clicks the terms and conditions checkbox.
    private void onAcceptTermsCheckboxClicked() {
        if (this.acceptTermsCheckBox.isChecked()) {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.TERMS_AND_CONDITIONS_URL)));
        }
    }

    // Method that is called when the user clicks the sign up button.
    private void onSignUpButtonClicked() {
        if (Utilities.emailIsValid(this.emailEditText.getText().toString()) && Utilities.passwordIsValid(this.passwordEditText.getText().toString()) && this.acceptTermsCheckBox.isChecked()) {
            this.signUp();
        } else if (!this.acceptTermsCheckBox.isChecked()) {
            Utilities.presentToast(this, this.getString(R.string.needAcceptTerms));
        } else {
            Utilities.presentToast(this, this.getString(R.string.fieldsNotValid));
        }
    }
    
    // Method that is called when the user clicks the Google sign up button.
    private void onGoogleSignUpButtonClicked() {
        if (!this.acceptTermsCheckBox.isChecked()) {
            Utilities.presentToast(this, this.getString(R.string.needAcceptTerms));
            return;
        }

        Log.d("Google sign up button", "Google sign up..."); // TODO
    }

    // Method that is called when the user clicks the Facebook sign up button.
    private void onFacebookSignUpButtonClicked() {
        if (!this.acceptTermsCheckBox.isChecked()) {
            Utilities.presentToast(this, this.getString(R.string.needAcceptTerms));
            return;
        }

        Log.d("Facebook sign up button", "Facebook sign up..."); // TODO
    }

    // Method that signs up.
    private void signUp() {
        this.progressBarContainer.setVisibility(View.VISIBLE);

        this.firebaseAuth.createUserWithEmailAndPassword(this.emailEditText.getText().toString(), this.passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progressBarContainer.setVisibility(View.GONE);
                            Utilities.presentToast(SignUpActivity.this, task.getException().getMessage());
                            return;
                        }

                        firebaseAuth.getCurrentUser().sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBarContainer.setVisibility(View.GONE);

                                        if (!task.isSuccessful()) {
                                            Utilities.presentToast(SignUpActivity.this, task.getException().getMessage());
                                            return;
                                        }

                                        Utilities.presentToast(SignUpActivity.this, SignUpActivity.this.getString(R.string.signedUp));
                                        SignUpActivity.this.finish();
                                    }
                                });
                    }
                });
    }
}
