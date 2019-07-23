package com.mgarciareimers.someApp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.activities.LoginActivity;
import com.mgarciareimers.someApp.commons.Utilities;

public class ProfileFragment extends Fragment {
    private Activity activity;

    private ConstraintLayout progressBarContainer;
    private ImageButton editButton, saveButton, cancelButton, signOutButton;
    private ImageView profileImageView;
    private EditText nameSurnameEditText, emailEditText;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public ProfileFragment(Activity activity, ConstraintLayout progressBarContainer) {
        this.activity = activity;
        this.progressBarContainer = progressBarContainer;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseUser = this.firebaseAuth.getCurrentUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        this.nameSurnameEditText = fragmentView.findViewById(R.id.nameSurnameEditText);
        this.emailEditText = fragmentView.findViewById(R.id.emailEditText);
        this.profileImageView = fragmentView.findViewById(R.id.profileImageView);
        this.editButton = fragmentView.findViewById(R.id.editButton);
        this.saveButton= fragmentView.findViewById(R.id.saveButton);
        this.cancelButton = fragmentView.findViewById(R.id.cancelButton);
        this.signOutButton = fragmentView.findViewById(R.id.signOutButton);

        this.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(true);
            }
        });

        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(false);
            }
        });

        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });

        this.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(activity, LoginActivity.class));
                activity.finish();
            }
        });

        return fragmentView;
    }

    // Method that is called when the user clicks the edit or cancel button.
    public void editProfile(boolean edit) {
        this.nameSurnameEditText.setEnabled(edit);

        if (edit) {
            this.editButton.setVisibility(View.GONE);
            this.signOutButton.setVisibility(View.GONE);
            this.saveButton.setVisibility(View.VISIBLE);
            this.cancelButton.setVisibility(View.VISIBLE);

            this.nameSurnameEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) this.activity.getSystemService(this.activity.INPUT_METHOD_SERVICE);
            imm.showSoftInput(this.nameSurnameEditText, InputMethodManager.SHOW_IMPLICIT);
        } else {
            this.editButton.setVisibility(View.VISIBLE);
            this.signOutButton.setVisibility(View.VISIBLE);
            this.saveButton.setVisibility(View.GONE);
            this.cancelButton.setVisibility(View.GONE);

            this.setFieldValues();
        }
    }

    // Method that is called when the user clicks the save button.
    private void saveProfile() {
        if (Utilities.emailIsValid(this.emailEditText.getText().toString()) && Utilities.nameIsValid(this.nameSurnameEditText.getText().toString())) {
            this.saveData();
        } else {
            Utilities.presentToast(this.activity, this.activity.getString(R.string.fieldsNotValid));
        }
    }

    // Method that saves the data in the database.
    private void saveData() {
        this.progressBarContainer.setVisibility(View.VISIBLE);

        boolean nameHasChanged = !this.nameSurnameEditText.getText().toString().equals(this.firebaseUser.getDisplayName());

        if (nameHasChanged) {
            UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(this.nameSurnameEditText.getText().toString())
                .build();

            this.firebaseUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        progressBarContainer.setVisibility(View.GONE);
                        Utilities.presentToast(activity, activity.getString(R.string.genericError));
                        editProfile(false);
                    } else {
                        progressBarContainer.setVisibility(View.GONE);
                        Utilities.presentToast(activity, activity.getString(R.string.savedData));
                        editProfile(false);
                    }
                }
            });
        } else {
            this.progressBarContainer.setVisibility(View.GONE);
            Utilities.presentToast(this.activity, this.activity.getString(R.string.noChangesRegistered));
        }
    }

    // Method that gets the user.
    public void getUser() {
        if (this.firebaseUser != null) {
            this.setFieldValues();

            if (!Utilities.nameIsValid(this.nameSurnameEditText.getText().toString())) {
                this.nameSurnameEditText.setError(this.activity.getString(R.string.noName));
            }
        }
    }

    // Method that sets the values of the fields.
    private void setFieldValues() {
        this.nameSurnameEditText.setText(this.firebaseUser.getDisplayName());
        this.emailEditText.setText(this.firebaseUser.getEmail());
    }
}
