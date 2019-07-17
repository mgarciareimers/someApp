package com.mgarciareimers.someApp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.adapters.ViewPagerAdapter;
import com.mgarciareimers.someApp.commons.Utilities;
import com.mgarciareimers.someApp.fragments.SearchUserFragment;
import com.mgarciareimers.someApp.model.User;
import com.mgarciareimers.someApp.services.ServerInterface;

import java.util.ArrayList;
import java.util.List;

public class TabsActivity extends AppCompatActivity {
    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;
    private ConstraintLayout progressBarContainer = null;

    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        this.getUsers();

        this.defineFields();
        this.defineFieldActions();
    }

    // Method that defines the fields.
    private void defineFields() {
        this.viewPager = this.findViewById(R.id.viewPager);
        this.tabLayout = this.findViewById(R.id.tabLayout);
        this.progressBarContainer = this.findViewById(R.id.progressBarContainer);
    }

    // Method that defines the actions of the fields.
    private void defineFieldActions() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new SearchUserFragment(this.userList), this.getString(R.string.searchUsers));
        adapter.addFragment(new SearchUserFragment(this.userList), this.getString(R.string.profile));

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
        switch(tab.getPosition()) {
            case 0:
                this.getUsers();
                Log.d("Exception", "Hello");
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    // Method that gets the list of users.
    private void getUsers() {
        this.progressBarContainer.setVisibility(View.VISIBLE);

        ServerInterface serverInterface = ServerInterface.getServerInterface(this);

        Response.Listener<String> responseCallback = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                try {
                    userList = gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                } catch (Exception e) {
                    Log.d("Exception", e.toString());
                    Utilities.presentToast(TabsActivity.this, e.toString());
                }

                progressBarContainer.setVisibility(View.GONE);
            }
        };

        Response.ErrorListener errorCallback = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.presentToast(TabsActivity.this, error.toString());
                progressBarContainer.setVisibility(View.GONE);
            }
        };

        serverInterface.getUsersList(responseCallback, errorCallback);
    }
}
