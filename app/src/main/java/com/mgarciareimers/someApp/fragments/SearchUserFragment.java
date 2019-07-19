package com.mgarciareimers.someApp.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.adapters.UserListAdapter;
import com.mgarciareimers.someApp.commons.Constants;
import com.mgarciareimers.someApp.commons.Utilities;
import com.mgarciareimers.someApp.model.User;
import com.mgarciareimers.someApp.services.ServerInterface;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchUserFragment extends Fragment {

    private Activity activity;
    private List<User> userList = new ArrayList<>();
    private List<Bitmap> bitmapList = new ArrayList<>();

    private GridView usersGridView;
    private ConstraintLayout progressBarContainer;
    private UserListAdapter userListAdapter;

    public SearchUserFragment(Activity context, ConstraintLayout progressBarContainer) {
        this.activity = context;
        this.progressBarContainer = progressBarContainer;
        this.getUsers();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_search_user, container, false);

        this.usersGridView = fragmentView.findViewById(R.id.usersGridView);
        this.userListAdapter = new UserListAdapter(this.activity, this.userList, this.bitmapList);
        this.usersGridView.setAdapter(userListAdapter);

        return fragmentView;
    }

    // Method that gets the list of users.
    public void getUsers() {
        this.progressBarContainer.setVisibility(View.VISIBLE);

        ServerInterface serverInterface = ServerInterface.getServerInterface(this.activity);

        Response.Listener<String> responseCallback = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                try {
                    userList = gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                    new GetRobots().execute(userList);
                } catch (Exception e) {
                    Utilities.presentToast(activity, e.toString());
                    progressBarContainer.setVisibility(View.GONE);
                }
            }
        };

        Response.ErrorListener errorCallback = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.presentToast(activity, error.toString());
                progressBarContainer.setVisibility(View.GONE);
            }
        };

        serverInterface.getUsersList(responseCallback, errorCallback);
    }

    // Class that gets the robots pictures (multi tasks).
    public class GetRobots extends AsyncTask<List<User>, String, List<Bitmap>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Bitmap> bitmaps) {
            if (bitmaps != null) {
                userListAdapter.notifyDataSetChanged(userList, bitmaps);
            } else {
                Utilities.presentToast(activity, activity.getString(R.string.genericError));
                activity.finish();
            }

            progressBarContainer.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<Bitmap> doInBackground(List<User>... lists) {
            List<Bitmap> bitmapList = new ArrayList<>();
            boolean isError = false;

            for (User user : lists[0]) {
                String src = Constants.ROBOHASH.concat(Integer.toString(user.getId())).concat(Constants.ROBOHASH_WIDTH);

                try {
                    URL url = new URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);
                    bitmapList.add(bitmap);
                } catch(Exception e) {
                    isError = true;
                    break;
                }
            }

            return isError ? null : bitmapList;
        }
    }


























    // Method that gets the pictures of the users in robot format.
    /*private void getRobotPictures() {
        boolean isError = false;

        for (User user : this.userList) {
            String src = Constants.ROBOHASH.concat(Integer.toString(user.getId())).concat(Constants.ROBOHASH_WIDTH);

            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                this.bitmapList.add(bitmap);
            } catch(Exception e) {
                isError = true;
                break;
            }
        }

        if (isError) {
            Utilities.presentToast(this.activity, this.activity.getString(R.string.genericError));
            this.activity.finish();
        } else {
            this.userListAdapter.notifyDataSetChanged(userList);
        }

        progressBarContainer.setVisibility(View.GONE);
    }*/
}
