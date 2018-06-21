package com.example.huntertsai.firebaseinclassdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Track_activity extends AppCompatActivity {
    private TextView mName;
    private RatingBar mratingBar;
    private EditText meditText;
    private DatabaseReference mdatabaseReference;
    private ListView mListView;
    private ArrayList<Track> mTrack;
    private int stars;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        mName = findViewById(R.id.track_artist);
        meditText = findViewById(R.id.track_input);
        mListView = findViewById(R.id.track_listview);
        mratingBar = findViewById(R.id.rating_bar);
        mTrack = new ArrayList<>();

        Intent intent = getIntent();
        mName.setText(intent.getStringExtra("name"));
        String artist_id = intent.getStringExtra("id");

        mdatabaseReference = FirebaseDatabase.getInstance().getReference("tracks").child(artist_id);//root

        mratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = mratingBar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    stars = (int)starsf + 1;
                    mratingBar.setRating(stars);

                    Toast.makeText(Track_activity.this, String.valueOf(stars), Toast.LENGTH_SHORT).show();
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }
                return true;
            }});

    }

    @Override
    protected void onStart() {
        super.onStart();

        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTrack.clear();
                for (DataSnapshot trackSnapshot : dataSnapshot.getChildren()){
                    Track track = trackSnapshot.getValue(Track.class);
                    mTrack.add(track);
                }

                TrackArrayAdapter mtrackArrayAdapter = new TrackArrayAdapter(Track_activity.this, mTrack);
                mListView.setAdapter(mtrackArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void add_track(View view) {
        String track_name = meditText.getText().toString().trim();
        String rating = String.valueOf(stars);
        String track_id = mdatabaseReference.push().getKey();
        Track mtrack = new Track(track_name, rating, track_id);

        mdatabaseReference.child(track_id).setValue(mtrack);
        meditText.setText("");
        mratingBar.setRating(0);
    }
}
