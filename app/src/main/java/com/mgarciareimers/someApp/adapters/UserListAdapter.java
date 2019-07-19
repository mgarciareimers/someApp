package com.mgarciareimers.someApp.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgarciareimers.someApp.R;
import com.mgarciareimers.someApp.model.User;

import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private Activity activity;
    private List<User> userList;
    private List<Bitmap> bitmapList;

    public UserListAdapter(Activity context, List<User> userList, List<Bitmap> bitmapList) {
        this.activity = context;
        this.userList = userList;
        this.bitmapList = bitmapList;
    }


    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public Object getItem(int index) {
        return index;
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(final int index, View view, ViewGroup viewGroup) {
        View listView = view;

        if (listView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.activity.getSystemService(this.activity.LAYOUT_INFLATER_SERVICE);
            listView = layoutInflater.inflate(R.layout.element_user, viewGroup, false);
        }

        ImageView userImageView = listView.findViewById(R.id.userImageView);
        userImageView.setImageBitmap(this.bitmapList.get(index));

        TextView userInfoTextView = listView.findViewById(R.id.userInfoTextView);

        userInfoTextView.setText(this.userList.get(index).getName());

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Item click:", Integer.toString(index));
            }
        });

        return listView;
    }

    public void notifyDataSetChanged(List<User> userList, List<Bitmap> bitmapList) {
        this.userList = userList;
        this.bitmapList = bitmapList;
        this.notifyDataSetChanged();
    }
}
