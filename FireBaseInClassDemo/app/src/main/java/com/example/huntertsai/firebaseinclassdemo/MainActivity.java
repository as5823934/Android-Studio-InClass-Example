package com.example.huntertsai.firebaseinclassdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText meditText;
    private DatabaseReference mdatabaseReference;
    private Spinner mSpinner;
    private ListView mListView;
    private ArrayList<Artist> mArtistlist;
    private Artist mArtist;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meditText = findViewById(R.id.artist_name_input);
        mSpinner = findViewById(R.id.spinner_genre);
        mListView = findViewById(R.id.list_view);
        mArtistlist = new ArrayList<>();

        mdatabaseReference = FirebaseDatabase.getInstance().getReference("artists");//root

    }

    @Override
    protected void onStart() {
        super.onStart();

        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                mArtistlist.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    mArtistlist.add(artist);
                }

                ArtistArrayAdapter mArtistArrayAdapter = new ArtistArrayAdapter(MainActivity.this, mArtistlist);
                mListView.setAdapter(mArtistArrayAdapter);

//                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Intent intent = new Intent(MainActivity.this, Track_activity.class);
//                        Artist artist = mArtistlist.get(i);
//                        String id = artist.getId();
//                        String name = artist.getName();
//
//                        intent.putExtra("id", id);
//                        intent.putExtra("name", name);
//                        startActivityForResult(intent, 1111);
//                        Toast("short");
//                    }
//                });
//
//                mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        final Artist artist = mArtistlist.get(i);
//
//                        Toast("long");
//                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                        builder.setTitle("Edit information");
//
//                        final EditText editText = new EditText(getBaseContext());
//                        final Spinner spinner = new Spinner(getBaseContext());
//                        List<String> list = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.genres));
//                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, list);
//                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        spinner.setAdapter(spinnerAdapter);
////                        spinnerAdapter.notifyDataSetChanged();
//
//                        LinearLayout linearLayout = new LinearLayout(getBaseContext());
//                        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT
//                        ));
//                        linearLayout.setOrientation(LinearLayout.VERTICAL);
//                        linearLayout.addView(editText);
//                        linearLayout.addView(spinner);
//
//                        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                String modfied_input = editText.getText().toString().trim();
//                                String spinner_input = spinner.getSelectedItem().toString();
//
//                                FirebaseDatabase.getInstance().getReference("artists").child(mArtist.getId()).child("name").setValue(modfied_input);
//                                FirebaseDatabase.getInstance().getReference("artists").child(mArtist.getId()).child("genre").setValue(spinner_input);
//
//                            }
//                        });
//                        builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                FirebaseDatabase.getInstance().getReference("artists").child(artist.getId()).setValue(null);
//                            }
//                        });
//
//                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
//
//                        builder.setView(linearLayout);
//                        builder.show();
//
//
//                        return true;
//                    }
//                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //error happens
                if (databaseError != null){
                    Toast(databaseError.getMessage());
                }
            }
        });
    }

    public void add_Artist(View view) {
        String artist_name = meditText.getText().toString().trim();
        String genres = mSpinner.getSelectedItem().toString();
        String id = mdatabaseReference.push().getKey(); //push()->auto gen key and get key
        Toast(artist_name + " Added");
        //get ref to database

        mArtist = new Artist(id, artist_name, genres);

        //store key value as a child of the root
        mdatabaseReference.child(id).setValue(mArtist);

        meditText.setText("");
        mSpinner.setSelection(0);

    }

    private void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
