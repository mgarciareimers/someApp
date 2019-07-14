package com.mgarciareimers.someApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.commons.Constants;
import com.mgarciareimers.someApp.commons.Utilities;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, nameSurnameEditText;
    private CheckBox acceptTermsCheckBox;
    private Button signUpButton;
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
        this.nameSurnameEditText = this.findViewById(R.id.nameSurname);
        this.acceptTermsCheckBox = this.findViewById(R.id.acceptTermsCheckBox);
        this.signUpButton = this.findViewById(R.id.signUpButton);
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
            Log.d("Sign up button", "Signing up...");
        } else {
            Utilities.presentToast(this, this.getString(R.string.fieldsNotValid));
        }
    }
}
