package com.mgarciareimers.someApp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.commons.Utilities;

public class ProfileFragment extends Fragment {
    private Activity activity;

    private ConstraintLayout progressBarContainer;
    private ImageButton editButton, saveButton, cancelButton;
    private ImageView profileImageView;
    private EditText nameSurnameEditText, emailEditText;

    public ProfileFragment(Activity activity, ConstraintLayout progressBarContainer) {
        this.activity = activity;
        this.progressBarContainer = progressBarContainer;
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


        return fragmentView;
    }

    // Method that is called when the user clicks the edit or cancel button.
    private void editProfile(boolean edit) {
        this.nameSurnameEditText.setEnabled(edit);
        this.emailEditText.setEnabled(edit);

        if (edit) {
            this.editButton.setVisibility(View.GONE);
            this.saveButton.setVisibility(View.VISIBLE);
            this.cancelButton.setVisibility(View.VISIBLE);
        } else {
            this.editButton.setVisibility(View.VISIBLE);
            this.saveButton.setVisibility(View.GONE);
            this.cancelButton.setVisibility(View.GONE);
        }
    }

    // Method that is called when the user clicks the save button.
    private void saveProfile() {
        if (Utilities.emailIsValid(this.emailEditText.getText().toString()) && Utilities.nameIsValid(this.nameSurnameEditText.getText().toString())) {
            this.editProfile(false);
            this.saveData();
        } else {
            Utilities.presentToast(this.activity, this.activity.getString(R.string.fieldsNotValid));
        }
    }

    // Method that saves the data in the database.
    private void saveData() {
        this.progressBarContainer.setVisibility(View.VISIBLE);

        this.progressBarContainer.setVisibility(View.GONE);
        Utilities.presentToast(this.activity, this.activity.getString(R.string.savedData));
    }
}
