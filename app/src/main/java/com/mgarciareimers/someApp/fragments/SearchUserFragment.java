package com.mgarciareimers.someApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.model.User;

import java.util.List;

public class SearchUserFragment extends Fragment {

    private List<User> userList;

    public SearchUserFragment(List<User> userList) {
        this.userList = userList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_user, container, false);
    }
}
