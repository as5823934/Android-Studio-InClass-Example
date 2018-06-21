package com.example.huntertsai.firebaseinclassdemo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by huntertsai on 2018-02-26.
 */

public class TrackArrayAdapter extends ArrayAdapter<Track> {

    private Context context;
    private List<Track> mTrack;

    public TrackArrayAdapter(@NonNull Context context, @NonNull List<Track> objects) {
        super(context, R.layout.track_list_layout, objects);
        this.context = context;
        this.mTrack = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Track mtrack = mTrack.get(position);
        String track_name = mtrack.getTrack();
        String rating = mtrack.getRating();

        LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
        View listView = layoutInflater.inflate(R.layout.track_list_layout, null);

        TextView tv_Track = listView.findViewById(R.id.track_name_list);
        TextView tv_rating = listView.findViewById(R.id.rating_list);

        tv_Track.setText(track_name);
        tv_rating.setText(rating);

        return listView;

    }
}
