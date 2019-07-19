package com.mgarciareimers.someApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.mgarciareimers.someApp.R;

public class ProfileFragment extends Fragment {
    private ConstraintLayout progressBarContainer = null;

    public ProfileFragment(ConstraintLayout progressBarContainer) {
        this.progressBarContainer = progressBarContainer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
