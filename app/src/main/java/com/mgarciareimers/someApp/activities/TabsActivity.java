package com.mgarciareimers.someApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mgarciareimers.someApp.R;

public class TabsActivity extends AppCompatActivity {
    private TabLayout tabLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        this.defineFields();
        this.defineFieldActions();
    }

    // Method that defines the fields.
    private void defineFields() {
        this.tabLayout = this.findViewById(R.id.tabLayout);
    }

    // Method that defines the actions of the fields.
    private void defineFieldActions() {
        // TODO
    }
}
