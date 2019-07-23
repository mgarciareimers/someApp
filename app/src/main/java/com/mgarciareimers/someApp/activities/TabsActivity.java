package com.mgarciareimers.someApp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.adapters.ViewPagerAdapter;
import com.mgarciareimers.someApp.fragments.ProfileFragment;
import com.mgarciareimers.someApp.fragments.SearchUserFragment;

public class TabsActivity extends AppCompatActivity {
    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;
    private ConstraintLayout progressBarContainer = null;

    private SearchUserFragment searchUserFragment = null;
    private ProfileFragment profileFragment = null;

    private int selectedTabId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        this.defineFields();
        this.defineFieldActions();
    }

    // Method that defines the fields.
    private void defineFields() {
        this.viewPager = this.findViewById(R.id.viewPager);
        this.tabLayout = this.findViewById(R.id.tabLayout);
        this.progressBarContainer = findViewById(R.id.progressBarDialog);

        this.createTabs();
    }

    // Method that creates the tabs.
    private void createTabs() {
        this.searchUserFragment = new SearchUserFragment(this, progressBarContainer);
        this.profileFragment = new ProfileFragment(this, progressBarContainer);
    }

    // Method that defines the actions of the fields.
    private void defineFieldActions() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(this.searchUserFragment, this.getString(R.string.searchUsers));
        adapter.addFragment(this.profileFragment, this.getString(R.string.profile));

        this.viewPager.setAdapter(adapter);

        this.tabLayout.setupWithViewPager(this.viewPager);

        // Listener.
        this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // Method that is called when the tab is selected.
    private void onTabItemSelected(TabLayout.Tab tab) {
        this.selectedTabId = tab.getPosition();

        switch (tab.getPosition()) {
            case 0:
                this.searchUserFragment.getUsers();
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
