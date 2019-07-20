package com.mgarciareimers.someApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.commons.Constants;
import com.mgarciareimers.someApp.commons.Utilities;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, nameSurnameEditText;
    private CheckBox acceptTermsCheckBox;
    private Button signUpButton, googleSignUpButton, facebookSignUpButton;
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
        this.nameSurnameEditText = this.findViewById(R.id.nameSurnameEditText);
        this.acceptTermsCheckBox = this.findViewById(R.id.acceptTermsCheckBox);
        this.signUpButton = this.findViewById(R.id.signUpButton);
        this.googleSignUpButton = this.findViewById(R.id.googleSignUpButton);
        this.facebookSignUpButton = this.findViewById(R.id.facebookSignUpButton);
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
        if (Utilities.emailIsValid(this.emailEditText.getText().toString()) && Utilities.passwordIsValid(this.passwordEditText.getText().toString()) && Utilities.nameIsValid(this.nameSurnameEditText.getText().toString()) && this.acceptTermsCheckBox.isChecked()) {
            Log.d("Sign up button", "Signing up..."); // TODO
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
}
