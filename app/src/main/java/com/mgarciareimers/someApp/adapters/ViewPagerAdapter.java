package com.mgarciareimers.someApp.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentListTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size(); // Return total pages.
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.fragmentListTitles.get(position);
    }

    // Method that adds the fragment to the lists.
    public void addFragment(Fragment fragment, String title) {
        this.fragmentList.add(fragment);
        this.fragmentListTitles.add(title);
    }
}
