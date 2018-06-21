package com.example.huntertsai.firebasedemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by huntertsai on 2018-02-24.
 */

public class listviewAdapter extends BaseAdapter{

    Activity activity;
    List<User> listUsers;
    LayoutInflater inflater;

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_view, null);

        TextView txtUser = (TextView) itemView.findViewById(R.id.textName);
        TextView txtEmail = (TextView) itemView.findViewById(R.id.textEmail);

        txtUser.setText(listUsers.get(i).getName());
        txtEmail.setText(listUsers.get(i).getEmail());
        return itemView;
    }
}
